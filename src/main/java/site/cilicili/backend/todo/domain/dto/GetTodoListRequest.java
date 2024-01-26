package site.cilicili.backend.todo.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.todo.domain.dto
 * Date:2024/1/26 20:31
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record GetTodoListRequest(
        boolean desc,
        String sortBy,
        @NotNull(message = "页面大小不能为空") Integer pageSize,
        @NotNull(message = "页码不能为空") Integer page) {
}
