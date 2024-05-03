package site.cilicili.backend.dept.domain.dto;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dept.domain.dto
 * Date:2023/12/31 21:59
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public record GetDeptListRequest(Boolean desc, Integer page, Integer pageSize, String sortBy) {
}
