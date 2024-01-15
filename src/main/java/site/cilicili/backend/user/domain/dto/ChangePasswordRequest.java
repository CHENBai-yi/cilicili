package site.cilicili.backend.user.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/9 21:40
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record ChangePasswordRequest(
        @NotNull String new_password_1, @NotNull String new_password_2, @NotNull String old_password) {
}
