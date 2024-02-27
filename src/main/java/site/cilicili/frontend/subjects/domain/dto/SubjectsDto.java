package site.cilicili.frontend.subjects.domain.dto;

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
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:40
 */

/**
 * (Subjects)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:40
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "Subjects数据传输类")
public class SubjectsDto implements Serializable {
    private static final long serialVersionUID = 600397510047227514L;
    List<Long> ids;
    @Schema(description = "ID")
    private Integer id;
    @Schema(description = "学科代码")
    private String subjectCode;
    @Schema(description = "学科名称")
    private String subjectName;
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
