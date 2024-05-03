package site.cilicili.backend.user.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/9 21:32
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record ChangeNicknameRequest(@NotNull String nickname) {
}
