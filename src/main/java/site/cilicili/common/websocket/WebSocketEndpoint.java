package site.cilicili.common.websocket;

import cn.hutool.json.JSONUtil;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.websocket
 * Date:2023/12/10 14:41
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@ServerEndpoint("/public/ws/admin")
@Slf4j
public class WebSocketEndpoint {

    @OnOpen
    public void onEstablished(Session session) {
        log.info("建立连接:{}", JSONUtil.toJsonStr(session));
    }
}
