package site.cilicili.backend.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
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
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysUserEntity {
        private List<AddUserRequestDto> dept;
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
