package site.cilicili.backend.config.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.util.List;

/**
 * (SysConfigFrontend)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysConfigFrontend数据传输类")
public class SysConfigFrontendDto implements Serializable {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysConfigFrontendEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<Records> children;
    }
}
