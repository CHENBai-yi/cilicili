package site.cilicili.backend.role.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dept.domain.dto.SysDeptDto;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.user.domain.dto.SysUserDto;

import java.io.Serializable;
import java.util.List;

/**
 * (SysRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:22
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRole数据传输类")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
public class SysRoleDto implements Serializable {
    private Object records;
    private Integer total;

    @Positive(message = "页码值不能为负.")
    private Integer page;

    @Positive(message = "页大小不能为负数.")
    private Integer pageSize;

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysRoleEntity {
        private String user;
        private String menu;
        private String button;
        private User createdByUser;
        private User updatedByUser;
        private DefaultPageMenu defaultPageMenu;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class DefaultPageMenu extends SysMenuEntity {
        private String role;
        private String children;
        private String button;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class User extends SysUserDto {
        private User createdByUser;
        private User updatedByUser;
        private List<Records> role;
        private List<SysDeptDto.Records> dept;
    }
}
