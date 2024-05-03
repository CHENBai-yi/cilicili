package site.cilicili.backend.dict.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dict.domain.dto
 * Date:2024/1/14 20:04
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryAndDeleteRequest(@NotNull Long id) {
}
