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
 * (SysButton)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysButton数据传输类")
public class SysButtonDto implements Serializable {
    private static final long serialVersionUID = -77875303948135030L;
    List<Long> ids;
    @Schema(description = "菜单Name")
    private String menuName;
    @Schema(description = "按钮名称")
    private String buttonName;
    @Schema(description = "按钮编码")
    private String buttonCode;
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
