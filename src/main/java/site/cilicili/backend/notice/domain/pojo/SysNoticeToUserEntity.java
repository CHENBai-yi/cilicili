package site.cilicili.backend.notice.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-25 15:55:22
 */

/**
 * (SysNoticeToUser)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:22
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysNoticeToUser 实体类")
@TableName("sys_notice_to_user")
public class SysNoticeToUserEntity implements Serializable {
    private static final long serialVersionUID = 567761947070243600L;

    @Schema(description = "消息ID")
    private String noticeId;

    @Schema(description = "接收用户")
    private String toUser;

    @Schema(description = "是否阅读")
    private String userRead;
}
