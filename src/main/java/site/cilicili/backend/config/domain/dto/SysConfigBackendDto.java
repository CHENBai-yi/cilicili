package site.cilicili.backend.config.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.util.List;

/**
 * (SysConfigBackend)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:43
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysConfigBackend数据传输类")
@AllArgsConstructor
@NoArgsConstructor
public class SysConfigBackendDto implements Serializable {
    private Object records;

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysConfigBackendEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<Records> children;
    }
}
