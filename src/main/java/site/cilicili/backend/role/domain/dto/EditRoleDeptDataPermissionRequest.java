package site.cilicili.backend.role.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/30 16:10
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record EditRoleDeptDataPermissionRequest(
        @NotNull String roleCode, String deptDataPermissionType, String deptDataPermissionCustom) {
}
