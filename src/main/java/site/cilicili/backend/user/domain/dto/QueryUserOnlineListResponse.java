package site.cilicili.backend.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.user.domain.pojo.SysUserOnlineEntity;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/18 21:03
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryUserOnlineListResponse {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class OnlineList extends SysUserOnlineEntity {
        private SysRoleDto.User user;
    }
}
