package site.cilicili.backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * (PluginExampleTestData)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "PluginExampleTestData数据传输类")
public class PluginExampleTestDataDto implements Serializable {
    private static final long serialVersionUID = -97418859577878815L;
    List<Long> ids;
    @Schema(description = "id")
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
    @Schema(description = "排序")
    private Long sort;
    @Schema(description = "系统内置")
    private String stable;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "备注描述")
    private String memo;
    @Schema(description = "第1列")
    private String column1;
    @Schema(description = "第2列")
    private String column2;
    @Schema(description = "第3列")
    private String column3;
    @Schema(description = "第4列")
    private String column4;
    @Schema(description = "第5列")
    private String column5;
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
