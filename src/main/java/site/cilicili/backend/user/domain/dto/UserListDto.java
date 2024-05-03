package site.cilicili.backend.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dept.domain.dto.SysDeptDto;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2023/12/31 15:15
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@Data
@Builder(toBuilder = true)
@Schema(description = "UserListDto数据传输类")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
public class UserListDto {
    private Integer page;
    private Integer pageSize;
    private Integer total;
    private List<Records> records;

    @Setter
    @Getter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysUserDto {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<SysRoleDto.Records> role;
        private List<SysDeptDto.Records> dept;
    }
}
