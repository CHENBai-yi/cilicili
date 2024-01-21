package site.cilicili.backend.log.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.log.domain.pojo.SysLogLoginEntity;
import site.cilicili.backend.log.domain.pojo.SysLogOperationEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.log.domain.dto
 * Date:2024/1/17 22:26
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryLogResponse {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonIgnoreProperties({"logoutTime", "username"})
    public static class LogLogin extends SysLogLoginEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class LogOperation extends SysLogOperationEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
    }

}
