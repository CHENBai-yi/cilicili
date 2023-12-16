package site.cilicili.backend.dict.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dict.domain.pojo.SysDictEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.util.List;

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

}
