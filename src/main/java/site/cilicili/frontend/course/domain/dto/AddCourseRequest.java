package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/2/27 0:06
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddCourseRequest extends CoursesEntity {
    private List<Catalog> catalog;
    private List<String> tags;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Catalog extends CatalogsEntity {
        private List<BarsEntity> bar;
    }
}
