package site.cilicili.backend.api.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.api.domain.dto
 * Date:2024/1/14 20:38
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record DeleteOrQueryApiRequest(@NotNull Long id) {
}
