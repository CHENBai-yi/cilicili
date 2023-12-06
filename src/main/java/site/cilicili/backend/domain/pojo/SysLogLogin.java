package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysLogLogin)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:41
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysLogLogin实体类")
public class SysLogLogin implements Serializable {
    private static final long serialVersionUID = 427373300176373843L;
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
    @Schema(description = "登录用户名")
    private String loginUsername;
    @Schema(description = "登录IP")
    private String loginIp;
    @Schema(description = "登录浏览器")
    private String loginBrowser;
    @Schema(description = "登录操作系统")
    private String loginOs;
    @Schema(description = "登录平台")
    private String loginPlatform;
    private String loginSuccess;
}


