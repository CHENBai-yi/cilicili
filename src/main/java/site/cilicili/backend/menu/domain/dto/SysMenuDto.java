package site.cilicili.backend.menu.domain.dto;

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
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysMenuEntity {
        private SysRoleDto role;
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<Records> children;
        private List<SysButtonEntity> button;
    }
}
