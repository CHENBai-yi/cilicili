package site.cilicili.backend.role.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/31 14:53
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record AddRoleUserRequest(@NotNull String roleCode, List<String> username) {
}
