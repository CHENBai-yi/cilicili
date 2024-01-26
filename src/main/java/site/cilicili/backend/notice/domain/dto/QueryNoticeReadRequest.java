package site.cilicili.backend.notice.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.notice.domain.dto
 * Date:2024/1/26 17:24
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryNoticeReadRequest(@NotNull(message = "消息id不能为空") @Positive(message = "id必须为正数") Long id) {
}
