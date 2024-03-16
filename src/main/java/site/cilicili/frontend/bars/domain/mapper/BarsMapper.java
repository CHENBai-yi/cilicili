package site.cilicili.frontend.bars.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.course.domain.dto.GetChildrenBarResponse;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.bars.domain.mapper
 * Date:2024/3/13 0:49
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Mapper
public interface BarsMapper {
    BarsMapper BARS_MAPPER = Mappers.getMapper(BarsMapper.class);

    @Mappings(
            value = {
                    @Mapping(source = "desc", target = "description"),
                    @Mapping(source = "catalog", target = "catalogId"),
                    @Mapping(source = "PId", target = "parentBarId")
            })
    BarsEntity barsToBarsEntity(GetChildrenBarResponse.Bar bar);

    List<BarsEntity> barsToBarsEntities(List<GetChildrenBarResponse.Bar> catalog);
}
