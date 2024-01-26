package site.cilicili.backend.notice.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JacksonInject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-25 15:55:20
 */

/**
 * (SysNotice)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:20
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysNotice 实体类")
@TableName("sys_notice")
public class SysNoticeEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -42203621225577429L;

    @Schema(description = "排序")
    @JacksonInject("sort")
    private Long sort;

    @Schema(description = "系统内置")
    @JacksonInject("stable")
    private String stable;

    @Schema(description = "状态")
    @JacksonInject("status")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "消息ID")
    private String noticeId;

    @Schema(description = "消息题目")
    private String noticeTitle;

    @Schema(description = "消息内容")
    @JacksonInject("noticeContent")
    private String noticeContent;

    @Schema(description = "消息类型")
    private String noticeType;

    @Schema(description = "已经发送")
    @JacksonInject("noticeSent")
    private String noticeSent;

    @Schema(description = "接收用户范围")
    private String noticeToUserType;

    @Schema(description = "接收用户范围")
    @TableField(exist = false)
    private List<String> noticeToUser;

    @TableField(exist = false)
    @JacksonInject("userRead")
    private String userRead;
}
