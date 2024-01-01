package site.cilicili.backend.user.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/1 22:19
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record ResetPasswordAndDeleteUserRequest(@NotNull Long id) {
}
