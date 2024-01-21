package site.cilicili.backend.log.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-17 21:11:44
 */

/**
 * (SysLogLogin)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:44
 */
@Slf4j
@Data
@Builder
@Schema(description = "SysLogLogin数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysLogLoginDto implements Serializable {
    private static final long serialVersionUID = 301166363665903128L;
    @Schema(description = "登录时间")
    private LocalDateTime createdAt;
    @Schema(description = "登出时间")
    private LocalDateTime updatedAt;
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
