package site.cilicili.frontend.carousel.domain.dto;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:45:46
 */

/**
 * (VideoCarousel)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:46
 */
@Slf4j
@Data
@ToString(doNotUseGetters = true)
@Schema(description = "VideoCarousel数据传输类")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoCarouselDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected String createdBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected String updatedBy;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime deletedAt;

    List<Long> ids;
    private Long id;
    private Long[] time;
    private String imageUrl;
    private String backgroundColor;
    private Long itemId;
    private Long catId;

    @JsonProperty("jump_type")
    private String type;

    private Integer sort;

    @JacksonInject("isShow")
    private Boolean isShow;

    private String memo;
    private String status;
    private String stable;

    @Pattern(regexp = "^http://.*?", message = "不是正确的链接地址")
    @JsonProperty("link_path")
    private String link;

    @JsonProperty("link_page")
    private String page;

    @JsonIgnore
    private Date start;

    @JsonIgnore
    private Date end;
    /**
     * 页
     */
    @JsonProperty("page")
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

    private String sortBy;
    private Boolean desc;

    @JsonGetter("time")
    public Long[] getJsonTime() {
        return new Long[]{start.getTime(), end.getTime()};
    }

    public Boolean getIsShow() {
        return "onOff_on".equals(status);
    }

    public Date getStart() {
        return DateUtil.date(time[0]);
    }

    public Date getEnd() {
        return DateUtil.date(time[1]);
    }

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
