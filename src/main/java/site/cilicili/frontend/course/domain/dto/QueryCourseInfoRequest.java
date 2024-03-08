package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/9 0:06
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryCourseInfoRequest {
    private boolean desc;
    private @NotNull Integer page;
    private @NotNull Integer pageSize;
    private String sortBy;
    private String status;
}
