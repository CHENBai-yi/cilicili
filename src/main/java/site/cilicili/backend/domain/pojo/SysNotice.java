package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysNotice)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysNotice实体类")
public class SysNotice implements Serializable {
    private static final long serialVersionUID = 734593675837233641L;
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


