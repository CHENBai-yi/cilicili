package site.cilicili.frontend.subjects.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.subjects.domain.dto
 * Date:2024/2/27 22:43
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EditCategoryRequest {
    String categoryName;
    Integer id;
    String status;
    Integer subjectId;
}
