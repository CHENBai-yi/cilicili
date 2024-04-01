package site.cilicili.frontend.common.pojo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.cilicili.frontend.course.domain.dto.GetCourseListResponse;

import java.io.Serializable;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.common.pojo
 * Date:2024/3/31 15:29
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@NoArgsConstructor
public class HotSearch implements Serializable {
    // 商品id
    private Double score;
    // 商品名称
    private GetCourseListResponse courseListResponse;

    @Builder
    public HotSearch(final Double score, final GetCourseListResponse courseListResponse) {
        this.score = score;
        this.courseListResponse = courseListResponse;
    }

    //.....等属性
}

