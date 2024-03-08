package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/8 10:12
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CetCoursesCountResponse {
    private Integer total;
    private Integer pass;
    private Integer on;
    private Integer off;
}
