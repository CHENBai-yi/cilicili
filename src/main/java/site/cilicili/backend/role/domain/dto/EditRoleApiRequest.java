package site.cilicili.backend.role.domain.dto;

import jakarta.validation.constraints.NotNull;
import site.cilicili.backend.api.domain.pojo.SysRoleApiEntity;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/31 14:20
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record EditRoleApiRequest(@NotNull String roleCode, List<SysRoleApiEntity> roleApi) {
}
