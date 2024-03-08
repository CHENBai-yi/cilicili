package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import site.cilicili.frontend.common.pojo.BaseQueryResponse;

import java.time.LocalDateTime;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/8 0:36
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetCourseInfoResponse extends BaseQueryResponse {
    @Builder
    public GetCourseInfoResponse(final Object records, final Integer page, final Integer pageSize, final Integer total) {
        super(records, page, pageSize, total);
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class CourseList {
        private int id;

        private String name;

        private String img;

        private String desc;

        private String author;

        private Integer carbs;

        private Integer protein;

        private String kind;

        private Double price;
        @JsonFormat(pattern = "yyyy年MM月dd日 HH:mm")
        private LocalDateTime date;
        private String status;
        private String reason;
    }
}
