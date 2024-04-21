package site.cilicili.frontend.memberShip.domain.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.log.domain.dto
 * Date:2024/1/17 22:18
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryMemberShipRequest(
        String loginSuccess,
        String loginUsername,
        String operationUsername,
        String sortBy,
        Boolean desc,
        @NotNull(message = "页码不能为空") @Positive(message = "页码不能为负数") Integer page,
        @NotNull(message = "页面大小不能为空") @Positive(message = "页面大小不能为负数") Integer pageSize) {
}
