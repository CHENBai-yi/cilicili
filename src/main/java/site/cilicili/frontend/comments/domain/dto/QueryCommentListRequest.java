package site.cilicili.frontend.comments.domain.dto;

import jakarta.validation.constraints.NotNull;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/30 16:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record QueryCommentListRequest(
        Boolean desc, Integer page, Integer pageSize, @NotNull Integer id, String sortBy) {
}
