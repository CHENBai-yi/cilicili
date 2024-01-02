package site.cilicili.backend.dept.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 16:36:33
 */

/**
 * (SysDept)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:33
 */
@Slf4j
@Data
@Builder(toBuilder = true)
@Schema(description = "SysDept数据传输类")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
public class SysDeptDto {
    private List<DeptListDto> records;
    private Integer total;

    @Positive(message = "页码值不能为负.")
    private Integer page;

    @Positive(message = "页大小不能为负数.")
    private Integer pageSize;

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysDeptEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class DeptListDto extends Records {
        private SysRoleDto.User leaderUser;
        private Object staff;
        private List<DeptListDto> children;
    }
}
