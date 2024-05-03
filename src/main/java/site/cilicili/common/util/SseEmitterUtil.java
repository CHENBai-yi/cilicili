package site.cilicili.common.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.util
 * Date:2024/5/2 13:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@Component
public class SseEmitterUtil {

    /**
     * 保存客户连接
     */
    public static Map<String, SseEmitter> userSseEmitters = new HashMap<>();

    /**
     * 推送消息给某个客户端
     *
     * @param userId  用户ID
     * @param content 消息体
     */
    public static void push(String userId, String content) {
        SseEmitter sseEmitter = userSseEmitters.get(userId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(content);
            } catch (Exception e) {
                log.error("用户-{} 推送消息异常：{}", userId, e);
            }
        } else {
            log.info("用户-{} 连接不存在", userId);
        }
    }

    /**
     * 推送消息给所有客户端
     *
     * @param content 消息体
     */
    public static void pushAllUser(String content) {
        for (SseEmitter emitter : userSseEmitters.values()) {
            try {
                emitter.send(content);
                emitter.send(SseEmitter.event().name("全局消息").data(content));
            } catch (Exception e) {
                log.error("推送消息异常：{}", e);
            }
        }
    }

    /**
     * 删除某个客户连接
     *
     * @param userId
     */
    public static void removeEmitter(String userId) {
        SseEmitter sseEmitter = userSseEmitters.get(userId);
        if (sseEmitter != null) {
            sseEmitter.complete();
            userSseEmitters.remove(userId);
        } else {
            log.info("用户-{} 连接不存在", userId);
        }
    }

    /**
     * 用户上线，开启一个SSE连接
     *
     * @param userId
     * @return
     */
    public SseEmitter subscribe(String userId) {
        // 创建一个超时时间为30秒的SseEmitter对象
        SseEmitter sseEmitter = new SseEmitter(30 * 1000L);
        // 设置回调函数
        sseEmitter.onCompletion(completionCallBack(userId));
        sseEmitter.onError(errorCallBack(userId));
        sseEmitter.onTimeout(timeoutCallBack(userId));
        // 缓存起来
        userSseEmitters.put(userId, sseEmitter);
        SseEmitterUtil.push(userId, "OK");
        return sseEmitter;
    }

    private Runnable completionCallBack(String userId) {
        return () -> {
            log.info("用户-{} 连接成功", userId);
        };
    }

    /**
     * 出现超时，将当前用户缓存删除
     *
     * @param userId
     * @return
     */
    private Runnable timeoutCallBack(String userId) {
        return () -> {
            log.info("用户-{} 连接超时", userId);
            userSseEmitters.remove(userId);
        };
    }

    /**
     * 出现异常，将当前用户缓存删除
     *
     * @param userId
     * @return
     */
    private Consumer<Throwable> errorCallBack(String userId) {
        return throwable -> {
            log.info("用户-{} 连接异常", userId);
            userSseEmitters.remove(userId);
        };
    }
}

