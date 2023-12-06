package site.cilicili.backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (SysUserRole)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysUserRole数据传输类")
public class SysUserRoleDto implements Serializable {
    private static final long serialVersionUID = -33696178733957235L;
    List<Long> ids;
    @Schema(description = "'角色编码'")
    private String sysRoleRoleCode;
    @Schema(description = "'用户名'")
    private String sysUserUsername;
    /**
     * 页
     */
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
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
}
