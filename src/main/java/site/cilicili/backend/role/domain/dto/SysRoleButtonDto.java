package site.cilicili.backend.role.domain.dto;

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
 * PROJECT: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-29 21:20:56
 */

/**
 * (SysRoleButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:56
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysRoleButton数据传输类")
public class SysRoleButtonDto implements Serializable {
    private static final long serialVersionUID = 360275249466672548L;
    List<Long> ids;
    private String sysRoleRoleCode;
    private String sysButtonButtonCode;
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