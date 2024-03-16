package site.cilicili.frontend.course.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.course.domain.pojo.CoursesEntity;
import site.cilicili.frontend.course.service.impl.CoursesServiceImpl;

import java.util.List;
import java.util.Optional;

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

    /**
     * @return
     */
    @Override
    public String getFirstBarUrl() {
        return Optional.of(catalog.get(0).getBar().get(0).getUrl()).orElse("");
    }

    /**
     * @return
     */
    @Override
    public String getTotalTime() {
        final Long total = catalog.stream().map(Catalog::getTotal).reduce(0L, Long::sum);
        System.out.println(total);
        return CoursesServiceImpl.formatDateTime(total);
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Catalog extends CatalogsEntity {
        private List<BarsEntity> bar;

        /**
         * @return
         */
        @Override
        public String getTotalTime() {
            return CoursesServiceImpl.formatDateTime(getTotal());
        }

        /**
         * @return
         */
        @Override
        public Long getTotal() {
            return bar.stream().map(BarsEntity::getSize).reduce(0L, Long::sum);
        }
    }
}
