package site.cilicili.frontend.carousel.domain.dto;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
    @JsonProperty(value = "page", access = JsonProperty.Access.WRITE_ONLY)
    private Integer pageNum;
    /**
     * 条
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sortBy;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Boolean desc;

    @JsonGetter("time")
    @SneakyThrows
    public Long[] getJsonTime() {
        return new Long[]{start.getTime(), end.getTime()};
    }

    @JsonSetter("status")
    public void setJsonStatus(String val) {
        this.status = val;
        this.isShow = Objects.nonNull(status) ? "onOff_on".equals(status) : isShow;
    }

    @JsonSetter("is_show")
    public void setJsonIsShow(Boolean val) {
        this.isShow = val;
        this.status = Objects.nonNull(isShow) ? (isShow ? "onOff_on" : "onOff_off") : status;
    }

    public Date getStart() {
        return Objects.nonNull(time) ? DateUtil.date(time[0]) : start;
    }

    public Date getEnd() {
        return Objects.nonNull(time) ? DateUtil.date(time[1]) : end;
    }

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
