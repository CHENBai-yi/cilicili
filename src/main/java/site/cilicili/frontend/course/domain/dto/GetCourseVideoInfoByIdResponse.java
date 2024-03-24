package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import site.cilicili.common.util.BarUtils;

import java.util.List;
import java.util.Objects;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/19 1:11
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetCourseVideoInfoByIdResponse {
    private List<VideoList> videoList;
    private Video video;
    private List<Catalog> catalog;

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Video {
        private String url;
        private String pic;
        private String thumbnails;
        private String title;
        private String author;
        @JsonIgnore
        private Double price;
        private String tag;
        private Long view;

        public String getTag() {
            return Objects.nonNull(price) ? (price > 0 ? " ￥" + price.toString() : "免费") : null;
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Content {
        private Video video;
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class VideoList {
        private String section;
        @JsonIgnore
        private Long barId;
        private String title;
        private Content content;

        public String getSection() {
            return String.format("第%1$s章", BarUtils.convert(barId.intValue()));
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Catalog {
        private String section;
        private String title;
        private String detail;
        private String time;
        private Integer total;
        private List<Detail> detailList;
        private Boolean show = true;

        @Data
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class Detail {
            private String tag;
            private String title;
            private String time;
        }
    }
}
