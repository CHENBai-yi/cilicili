package site.cilicili.backend.todo.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.todo.domain.pojo.SysTodoEntity;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.todo.domain.dto
 * Date:2024/1/26 20:35
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TodoListResponse {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Record extends SysTodoEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
    }
}
