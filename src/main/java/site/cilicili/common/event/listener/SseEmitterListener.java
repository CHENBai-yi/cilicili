package site.cilicili.common.event.listener;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import site.cilicili.common.util.R;
import site.cilicili.common.util.SseEmitterUtil;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.event.listener
 * Date:2024/5/2 13:31
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@RequiredArgsConstructor
public class SseEmitterListener {
    private final SseEmitterUtil sseEmitterUtil;

    @SuppressWarnings("all")
    @EventListener(classes = SseEmitterUtil.class)
    public void onUserLogin(SseEmitterUtil sseEmitterUtil) {
        String message = "用户 " + "bbb" + " 上线了";
        this.sseEmitterUtil.pushAllUser(JSONUtil.toJsonStr(R.yes("Success.")));
    }
}
