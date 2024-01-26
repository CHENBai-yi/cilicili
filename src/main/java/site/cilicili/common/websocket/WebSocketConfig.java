package site.cilicili.common.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.websocket
 * Date:2023/12/10 14:40
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
