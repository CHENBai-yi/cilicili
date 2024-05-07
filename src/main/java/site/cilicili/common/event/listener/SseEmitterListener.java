package site.cilicili.common.event.listener;

import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
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
    @EventListener(classes = HttpStatus.class)
    public void onUserLogin(HttpStatus httpStatus) {
        final R yes = R.yes("Success.");
        yes.setCode(httpStatus.value());
        this.sseEmitterUtil.pushAllUser(JSONUtil.toJsonStr(yes));
    }
}
