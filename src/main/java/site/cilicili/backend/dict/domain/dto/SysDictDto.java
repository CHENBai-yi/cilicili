package site.cilicili.backend.dict.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONArray;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dict.domain.pojo.SysDictEntity;
import site.cilicili.backend.menu.domain.dto.SysMenuDto;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * (SysDict)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 12:44:36
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysDict数据传输类")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
public class SysDictDto implements Serializable {
    private Object records;

    @Getter
    @Setter
    @ToString
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysDictEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<Records> children;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class AddOrEditResponse extends SysDictEntity {
        private SysMenuDto.CreatedByUser createdByUser;
        private List<Children> children;

        @JsonProperty("children")
        public void setCh(final JSONArray objectMap) {
            this.children = BeanUtil.copyToList(objectMap, Children.class);
        }

        @JsonProperty("created_by_user")
        public void setCreateUser(final Map<String, Object> objectMap) {
            this.createdByUser = BeanUtil.toBeanIgnoreCase(objectMap, SysMenuDto.CreatedByUser.class, true);
        }

        @Getter
        @Setter
        @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
        public static class Children extends SysDictEntity {
            private SysMenuDto.CreatedByUser createdByUser;
        }
    }
}
