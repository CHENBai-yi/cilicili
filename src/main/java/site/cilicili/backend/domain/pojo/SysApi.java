package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysApi实体类")
public class SysApi implements Serializable {
    private static final long serialVersionUID = -40861941090069659L;
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
    @Schema(description = "Api分组")
    private String apiGroup;
    @Schema(description = "请求方法")
    private String apiMethod;
    @Schema(description = "Api地址")
    private String apiPath;
}


