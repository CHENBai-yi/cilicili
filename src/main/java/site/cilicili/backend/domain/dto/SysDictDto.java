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
 * (SysDict)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysDict数据传输类")
public class SysDictDto implements Serializable {
    private static final long serialVersionUID = 786981774663001770L;
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
    @Schema(description = "父字典编码")
    private String parentCode;
    @Schema(description = "字典编码")
    private String dictCode;
    @Schema(description = "字典名称")
    private String dictLabel;
    @Schema(description = "字典扩展项1")
    private String dictExt1;
    @Schema(description = "字典扩展项2")
    private String dictExt2;
    @Schema(description = "字典扩展项3")
    private String dictExt3;
    @Schema(description = "字典扩展项4")
    private String dictExt4;
    @Schema(description = "字典扩展项5")
    private String dictExt5;
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
