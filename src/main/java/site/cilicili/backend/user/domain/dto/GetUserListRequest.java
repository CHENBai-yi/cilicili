package site.cilicili.backend.user.domain.dto;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2023/12/31 15:12
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */

public record GetUserListRequest(String deptCode, Boolean desc, Integer page, Integer pageSize, String sortBy,
                                 Boolean withAdmin, String realName, String username) {
}
