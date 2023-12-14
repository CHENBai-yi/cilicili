package site.cilicili.backend.role.domain.dto;

import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.role.domain.dto
 * Date:2023/12/14 4:15
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class RoleListQueryParam {
    private boolean desc;
    private String sortBy;
    private String roleCode;
    private String roleName;
    private Integer page;
    private Integer pageSize;
}
