package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/25 22:48
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class GetSubjectCategoriesResponse {

    private List<String> subject;
    private List<String> classify;
    private List<String> classifyMore;
    private List<Courses> courses;

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Courses {
        private Integer id;
        private String title;
        private String subtitle;
        private String price;
        private String pic;
        private Long view = 99L;
        private Long stars = 4L;
        private Long dm;
        private String faver;
        private String author;
        private String time;

        public String getPrice() {
            return "￥ " + (Objects.nonNull(price) ? (Double.parseDouble(price) > 0 ? price : "免费") : "体验课");
        }

        public Long getStars() {
            if (view > 500) {
                stars = 5L;
            } else if (view > 400) {
                stars = 4L;
            } else if (view > 300) {
                stars = 3L;
            } else if (view > 200) {
                stars = 2L;
            } else {
                stars = 1L;
            }
            return stars;
        }
    }
}
