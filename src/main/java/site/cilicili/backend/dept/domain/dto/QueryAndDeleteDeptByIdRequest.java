package site.cilicili.backend.dept.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dept.domain.dto
 * Date:2024/1/9 22:53
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryAndDeleteDeptByIdRequest(@NotNull Long id) {
}
