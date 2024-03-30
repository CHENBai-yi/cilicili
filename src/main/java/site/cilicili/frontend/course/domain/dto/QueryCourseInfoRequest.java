package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
@JsonIgnoreProperties(value = {"createdBy"})
public class QueryCourseInfoRequest {
    private String createdBy;
    private boolean desc;
    private @NotNull Integer page;
    private @NotNull Integer pageSize;
    private String sortBy;
    private String status;
    private String status1;
    private String kind;
    private String teacher;
    private List<String> s;

    public List<String> getS() {
        return Optional.ofNullable(status1).map(status -> "yes".equals(status.split("_")[1]) ? List.of("onOffPass_on") : Arrays.asList("onOffPass_off", "onOffPass_pass")).orElse(List.of(""));
    }
}
