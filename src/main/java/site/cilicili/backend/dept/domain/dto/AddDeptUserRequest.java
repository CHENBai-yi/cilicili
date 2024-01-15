package site.cilicili.backend.dept.domain.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dept.domain.dto
 * Date:2024/1/10 13:20
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record AddDeptUserRequest(@NotNull String deptCode, List<String> username) {
}
