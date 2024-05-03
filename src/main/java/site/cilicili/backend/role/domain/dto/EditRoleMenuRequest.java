package site.cilicili.backend.role.domain.dto;

import jakarta.validation.constraints.NotNull;
import site.cilicili.backend.role.domain.pojo.SysRoleButtonEntity;
import site.cilicili.backend.role.domain.pojo.SysRoleMenuEntity;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/31 13:55
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record EditRoleMenuRequest(
        String defaultPage,
        @NotNull String roleCode,
        List<SysRoleButtonEntity> roleButton,
        List<SysRoleMenuEntity> roleMenu) {
}
