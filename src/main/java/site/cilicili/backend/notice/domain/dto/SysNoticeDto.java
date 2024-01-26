package site.cilicili.backend.notice.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-25 15:55:21
 */

/**
 * (SysNotice)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:21
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysNotice数据传输类")
@NoArgsConstructor
@AllArgsConstructor
public class SysNoticeDto implements Serializable {
    private static final long serialVersionUID = -44006760769443246L;

    @Schema(description = "id")
    private Long id;

    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "消息ID")
    private String noticeId;

    @Schema(description = "消息题目")
    private String noticeTitle;

    @Schema(description = "消息内容")
    private String noticeContent;

    @Schema(description = "消息类型")
    private String noticeType;

    @Schema(description = "已经发送")
    private String noticeSent;

    @Schema(description = "接收用户范围")
    private String noticeToUserType;
}
