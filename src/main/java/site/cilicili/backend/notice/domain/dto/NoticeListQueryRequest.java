package site.cilicili.backend.notice.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/14 4:15
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record NoticeListQueryRequest(
        boolean desc,
        String sortBy,
        String noticeSent,
        String noticeRead,
        String noticeTitle,
        String noticeType,
        String noticeToUser,
        @NotNull(message = "页面大小不能为空") Integer pageSize,
        @NotNull(message = "页码不能为空") Integer page) {
}
