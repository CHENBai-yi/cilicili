package site.cilicili.frontend.categories.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.cilicili.frontend.categories.domain.pojo.CategoriesEntity;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.categories.domain.dto
 * Date:2024/2/27 23:31
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddCategoryRequest extends CategoriesEntity {
    private String subjectCode;
}
