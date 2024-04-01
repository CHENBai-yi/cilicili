package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/16 23:07
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class GetCourseListResponse implements Serializable {
    @Serial
    @JsonIgnore
    private static final long serialVersionUID = -4035754716493701436L;
    private String view;
    private Long dm;
    private Integer id;
    private String title;
    private String faver;
    private String author;
    private String time;
    private Detail detail;

    @Data
    public static class Detail {
        private Video video;
        private DanMaKu danmaku;
        private List<HighLight> highlight;
    }

    @Data
    public static class Video {
        private String pic;
        private String url;
        private String type = "auto";
    }

    @Data
    public static class DanMaKu {
        private String id;
        private String api;
        private String token;
        private String maximum;
        private List<String> addition;
        private String user;
        private String bottom;
        private Boolean unlimited;
        private Double speedRate;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class HighLight {
        private Long time;
        private String text;
    }
}
