package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysLogOperation)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:41
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysLogOperation实体类")
public class SysLogOperation implements Serializable {
    private static final long serialVersionUID = -81181540000583583L;
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
    @Schema(description = "请求用户名")
    private String operationUsername;
    @Schema(description = "请求IP")
    private String operationIp;
    @Schema(description = "请求方法")
    private String operationMethod;
    @Schema(description = "请求Api")
    private String operationApi;
    @Schema(description = "请求状态")
    private Long operationStatus;
    @Schema(description = "body")
    private String operationBody;
}


