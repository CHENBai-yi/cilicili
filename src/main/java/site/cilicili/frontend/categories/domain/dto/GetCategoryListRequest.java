package site.cilicili.frontend.categories.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.categories.domain.dto
 * Date:2024/2/27 20:57
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetCategoryListRequest {
    String categoryName;
    Boolean desc;
    Integer page;
    Integer pageSize;

    String sortBy;
    String subjectCode;
    Boolean withAdmin;
}
