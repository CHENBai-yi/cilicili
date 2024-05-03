package site.cilicili.common.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.websocket
 * Date:2023/12/10 14:41
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@ServerEndpoint("/public/ws/{username}")
@Slf4j
@EqualsAndHashCode
public class WebSocketEndpoint {

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
     * 虽然@Component默认是单例模式的，但springboot还是会为每个websocket连接初始化一个bean，所以可以用一个静态set保存起来。
     */
    private static final CopyOnWriteArraySet<WebSocketEndpoint> WEB_SOCKETS = new CopyOnWriteArraySet<>();
    /**
     * 用来存在线连接用户信息
     */
    private static final ConcurrentHashMap<String, Session> SESSION_POOL = new ConcurrentHashMap<>();

    private static ObjectMapper objectMapper;
    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;
    /**
     * 用户ID
     */
    private Long userId;

    private String username;
    ;

    @Autowired
    public void setObjectMapper(final ObjectMapper objectMapper) {
        WebSocketEndpoint.objectMapper = objectMapper;
    }

    /**
     * 链接成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "username") String userId) {
        this.session = session;
        this.username = userId;
        WEB_SOCKETS.add(this);
        SESSION_POOL.put(username, session);
        log.info("建立与Username：{}的消息提醒计数连接", userId);
    }

    /**
     * 链接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        WEB_SOCKETS.remove(this);
        SESSION_POOL.remove(this.username);
        log.info("关闭与UserID：{}的消息提醒计数连接", userId);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message) throws JsonProcessingException {
        log.info("接收到UserID：{}的消息{}", userId, message);
        sendAllMessage(message);
    }

    /**
     * 发送错误时的处理
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发送到UserID：{}的消息传输失败", userId, error);
    }

    /**
     * 广播消息
     *
     * @param message
     */
    public void sendAllMessage(CiliMessage message) {
        try {
            for (WebSocketEndpoint socketServer : WEB_SOCKETS) {
                if (socketServer.session.isOpen()) {
                    socketServer.session.getAsyncRemote().sendText(objectMapper.writeValueAsString(message));
                }
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 广播消息
     * 群聊
     *
     * @param message
     */
    public void sendAllMessage(String message) {

        try {
            final CiliMessage ciliMessage = objectMapper.readValue(message, CiliMessage.class);
            ciliMessage.setStamp(Date.from(Instant.now()));
            for (WebSocketEndpoint socketServer : WEB_SOCKETS) {
                if (socketServer.session.isOpen()) {
                    socketServer.session.getAsyncRemote().sendText(objectMapper.writeValueAsString(ciliMessage));
                }
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 单人单播消息
     */
    public Boolean sendOneMessage(String userId, CiliMessage message) {
        boolean flag = true;
        try {
            Session session = SESSION_POOL.get(userId);
            if (session != null && session.isOpen()) {
                session.getAsyncRemote().sendText(objectMapper.writeValueAsString(message));
            }
            return flag;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return !flag;
    }

    /**
     * 多人单播消息
     */
    public Boolean sendMoreMessage(List<String> userIds, CiliMessage message) {

        boolean flag = true;
        try {
            for (String userId : userIds) {
                Session session = SESSION_POOL.get(userId);
                if (session != null && session.isOpen()) {
                    session.getAsyncRemote().sendText(objectMapper.writeValueAsString(message));
                }
            }
            return flag;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return !flag;
    }
}
