package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

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
    }
}
