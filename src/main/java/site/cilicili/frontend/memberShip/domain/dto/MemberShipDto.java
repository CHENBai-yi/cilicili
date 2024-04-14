package site.cilicili.frontend.memberShip.domain.dto;

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
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-13 20:20:56
 */

/**
 * (MemberShip)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:56
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "MemberShip数据传输类")
public class MemberShipDto implements Serializable {
    private static final long serialVersionUID = 399862768936488873L;
    List<Long> ids;
    private Integer id;
    @Schema(description = "会员id")
    private Long userId;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    @Schema(description = "会员名")
    private String username;
    private String email;
    private String status;
    private Integer logicalDelete;
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
