package site.cilicili.common.db.domain.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;

import java.util.List;

/**
 * PROJECT:chart
 * PACkAGE:com.example.db.domain
 * Date:2023/11/27 19:34
 * EMAIL:
 *
 * @author BaiYiChen
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DbSourceToDruidDataSource {
    DbSourceToDruidDataSource DB_SOURCE_TO_DRUID_DATA_SOURCE = Mappers.getMapper(DbSourceToDruidDataSource.class);

    @Mappings({
            @Mapping(
                    target = "url",
                    expression = "java(String.join(\"/\", databaseConnection.getUrl(), databaseConnection.getScheme()))"),
            @Mapping(target = "driver", ignore = true)
    })
    DruidDataSource toDruidDataSource(DatabaseConnection databaseConnection);

    List<DruidDataSource> toDruidDataSourceAList(List<DatabaseConnection> databaseConnection);
}
