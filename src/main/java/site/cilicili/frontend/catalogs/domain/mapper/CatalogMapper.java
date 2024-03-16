package site.cilicili.frontend.catalogs.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.course.domain.dto.GetChildrenBarResponse;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.mapper
 * Date:2024/3/13 0:44
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Mapper
public interface CatalogMapper {
    CatalogMapper CATALOG_MAPPER = Mappers.getMapper(CatalogMapper.class);

    @Mappings(value = {@Mapping(source = "desc", target = "description"), @Mapping(target = "courseId", ignore = true)})
    CatalogsEntity cataLogsToCatalogsEntity(GetChildrenBarResponse.Catalog catalog);

    List<CatalogsEntity> cataLogsToCatalogsEntities(List<GetChildrenBarResponse.Catalog> catalog);
}
