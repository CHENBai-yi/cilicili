package site.cilicili.backend.menu.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * (SysMenu)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:02:00
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysMenu数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysMenuDto implements Serializable {
    private static final long serialVersionUID = -96896152350797501L;
    private List<Records> records;
    /**
     * 页
     */
    @JsonProperty(value = "page")
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    @Getter
    @Setter
    public static class Records extends SysMenuEntity {
        private SysRoleDto role;
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<Records> children;
        private List<SysButtonEntity> button;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class EditMenuRequest extends SysMenuEntity {
        private CreatedByUser createdByUser;
        private UpdatedByUser updatedByUser;
        private List<SysButtonEntity> button;
        private List<EditMenuRequest> children;

        @JsonProperty(value = "created_by_user")
        public void setCreatedUser(final Map<String, Object> objectMap) {
            this.createdByUser = BeanUtil.toBeanIgnoreCase(objectMap, CreatedByUser.class, true);
        }

        @JsonProperty(value = "updated_by_user")
        public void setUpdatedUser(final Map<String, Object> objectMap) {
            this.updatedByUser = BeanUtil.toBeanIgnoreCase(objectMap, UpdatedByUser.class, true);
        }
    }

    @Getter
    @Setter
    public static class CreatedByUser extends SysUserEntity {
        private Long createdByUser;
        private List<Integer> dept;
        private List<Integer> role;
        private User updatedByUser;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class UpdatedByUser extends SysUserEntity {
        private Long updatedByUser;
        private List<Integer> dept;
        private List<Integer> role;
        private User createdByUser;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class User extends SysUserEntity {
        private Long updatedByUser;
        private Long createdByUser;
        private List<Dept> dept;
        private List<Role> role;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Role extends SysRoleEntity {
        private DefaultPageMenu defaultPageMenu;

        @Getter
        @Setter
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
        public static class DefaultPageMenu extends SysMenuEntity {
            private String role;
            private String children;
            private String button;
        }
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Dept extends SysDeptEntity {
    }
}
