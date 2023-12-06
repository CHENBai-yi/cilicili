package site.cilicili.backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * (SysLogOperation)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:42
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysLogOperation数据传输类")
public class SysLogOperationDto implements Serializable {
    private static final long serialVersionUID = 895219779367070619L;
    List<Long> ids;
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
    /**
     * 页
     */
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
