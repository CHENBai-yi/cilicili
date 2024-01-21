package site.cilicili.backend.log.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.log.domain.dto
 * Date:2024/1/17 22:44
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record DeleteLogRequest(@NotNull(message = "id不能为空") Long id) {
}
