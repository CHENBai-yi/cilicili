package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (SysNoticeToUser)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysNoticeToUser实体类")
public class SysNoticeToUser implements Serializable {
    private static final long serialVersionUID = -59707646502742642L;
    @Schema(description = "消息ID")
    private String noticeId;
    @Schema(description = "接收用户")
    private String toUser;
    @Schema(description = "是否阅读")
    private String userRead;
}


