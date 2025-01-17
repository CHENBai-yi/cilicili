package site.cilicili.backend.api.domain.dto;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.api.domain.dto
 * Date:2023/12/30 15:30
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record GetApiListRequest(
        Boolean desc, Integer page, Integer pageSize, String sortBy, String apiGroup, String apiMethod) {
}
