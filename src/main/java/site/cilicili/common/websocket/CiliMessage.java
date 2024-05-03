package site.cilicili.common.websocket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.websocket
 * Date:2024/1/26 22:16
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CiliMessage {

    private String avatar;
    private String messageToUserType;
    private String messageType;
    private String name;
    private Date stamp;
    private String text;
    private List<String> toUser;
}
