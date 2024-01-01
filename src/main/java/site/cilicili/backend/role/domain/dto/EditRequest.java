package site.cilicili.backend.role.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/29 19:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record EditRequest(@NotNull long id, long sort, String roleName, String memo) {
}
