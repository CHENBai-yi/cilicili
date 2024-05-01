package site.cilicili.frontend.advertising.domain.dto;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:46:16
 */

/**
 * (VideoAdvertising)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Accessors(chain = true)
@Schema(description = "VideoAdvertising数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoAdvertisingDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 283647449419855606L;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JacksonInject("isShow")
    private Boolean isShow;
    private Long id;
    private Long[] time;
    private Long sort;
    private String stable;
    private String status;
    private String memo;
    @JsonProperty("content_type")
    private String adverType;
    @JsonProperty("adver_page")
    private String adverUrl;
    @JsonProperty("content")
    private String adverContent;
    @Pattern(regexp = "^http://.*?", message = "不是正确的链接地址")
    @JsonProperty("adver_path")
    private String adverLink;
    private Date start;
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

    @JsonGetter("time")
    @SneakyThrows
    public Long[] getJsonTime() {
        return new Long[]{start.getTime(), end.getTime()};
    }

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
