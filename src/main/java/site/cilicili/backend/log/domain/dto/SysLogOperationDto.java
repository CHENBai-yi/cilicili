package site.cilicili.backend.log.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-17 21:12:57
 */

/**
 * (SysLogOperation)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:12:57
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysLogOperation数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysLogOperationDto implements Serializable {
    private static final long serialVersionUID = 596832656222004077L;
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
