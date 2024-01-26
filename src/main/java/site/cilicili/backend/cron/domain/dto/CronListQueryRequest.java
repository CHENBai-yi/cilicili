package site.cilicili.backend.cron.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.cron.domain.dto
 * Date:2024/1/27 1:24
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record CronListQueryRequest(
        boolean desc,
        String sortBy,
        @NotNull(message = "页面大小不能为空") Integer pageSize,
        @NotNull(message = "页码不能为空") Integer page) {
}
