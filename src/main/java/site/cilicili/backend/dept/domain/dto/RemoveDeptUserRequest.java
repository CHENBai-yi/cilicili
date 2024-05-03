package site.cilicili.backend.dept.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dept.domain.dto
 * Date:2024/1/10 14:29
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record RemoveDeptUserRequest(@NotNull String deptCode, @NotNull String username) {
}
