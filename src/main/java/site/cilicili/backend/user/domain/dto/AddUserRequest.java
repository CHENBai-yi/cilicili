package site.cilicili.backend.user.domain.dto;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.domain.dto
 * Date:2024/1/1 15:40
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@Data
@Builder(toBuilder = true)
@Schema(description = "AddUserRequest数据传输类")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
public class AddUserRequest {
    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Records extends SysUserEntity {
        private List<DeptDto> dept;
        @JsonProperty(value = "dept")
        public void setDept(final JSONArray dept) {
            this.dept = JSONUtil.toList(dept, DeptDto.class);
        }
    }


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DeptDto extends DeptDtoBase {
        private UserDto createdByUser;
        private List<DeptDtoBase> children;

        @JsonProperty(value = "children")
        public void setChildren(final JSONArray children) {
            this.children = JSONUtil.toList(children, DeptDtoBase.class);
        }

        @JsonProperty(value = "createdByUser")
        public void setCreatedByUser(final JSONObject children) {
            this.createdByUser = JSONUtil.toBean(children, UserDto.class);
        }

    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class DeptDtoBase extends SysDeptEntity {
        private Object leaderUser;
        // private Integer createdByUser;
    }

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserDto extends SysUserEntity {
        private Integer createdByUser;
        private List<SysDeptEntity> dept;
        private List<SysRoleDto.Records> role;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class AddUserRequestDto extends SysDeptEntity {
        private Records createdByUser;
        private Records updatedByUser;
        private Records leaderUser;
        private Object staff;
        private List<AddUserRequestDto> children;
    }
}
