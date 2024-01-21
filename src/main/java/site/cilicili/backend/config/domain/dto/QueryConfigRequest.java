package site.cilicili.backend.config.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.config.domain.dto
 * Date:2024/1/17 16:09
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryConfigRequest(
        String configItem,
        String memo,
        String sortBy,
        Boolean desc,
        @NotNull(message = "页面大小不能为空") Integer pageSize,
        @NotNull(message = "页码不能为空") Integer page
) {
}
