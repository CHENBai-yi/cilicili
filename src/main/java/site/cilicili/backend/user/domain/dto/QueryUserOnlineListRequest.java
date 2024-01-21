package site.cilicili.backend.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/18 20:55
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record QueryUserOnlineListRequest(
        Boolean desc,
        String sortBy,
        String username,
        @NotNull(message = "页码不能为空") @Positive(message = "页码不能为负数") Integer page,
        @NotNull(message = "页面大小不能为空") @Positive(message = "页面大小不能为负数") Integer pageSize) {
}
