package site.cilicili.backend.menu.domain.dto;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.menu.domain.dto
 * Date:2023/12/29 21:54
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record GetMenuListRequest(Boolean desc, Integer page, Integer pageSize, String sortBy) {
}
