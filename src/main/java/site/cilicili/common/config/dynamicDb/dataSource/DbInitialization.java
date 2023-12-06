package site.cilicili.common.config.dynamicDb.dataSource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import site.cilicili.common.config.dynamicDb.MyDataSourceList;
import site.cilicili.common.db.domain.mapper.DbSourceToDruidDataSource;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;
import site.cilicili.common.db.service.DatabaseConnectionService;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PROJECT:chart
 * PACkAGE:com.example.config.dynamicDb.dataSource
 * Date:2023/11/27 19:13
 * EMAIL:
 *
 * @author BaiYiChen
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DbInitialization implements CommandLineRunner {
    private final DatabaseConnectionService databaseConnectionService;
    private final MyDataSourceList dataSourceList;
    private final List<DatabaseConnection> connections = new ArrayList<>();

    /**
     * @param args incoming main method arguments
     * @descrption 数据库信息配置多数据源
     */
    @Override
    public void run(final String... args) {

        try {
            List<DatabaseConnection> connections1 = databaseConnectionService.list();
            connections.addAll(connections1);

        } catch (Exception ignored) {
        }
        Optional.ofNullable(connections).ifPresent(databaseConnections -> {
            final Map<Object, Object> dataSourceMap = databaseConnections.stream().collect(Collectors.toMap(DatabaseConnection::getScheme, connection -> {
                final DruidDataSource druidDataSource = DbSourceToDruidDataSource.DB_SOURCE_TO_DRUID_DATA_SOURCE.toDruidDataSource(connection);
                druidDataSource.setDriverClassName(connection.getDriver());
                druidDataSource.setDefaultAutoCommit(true);
                druidDataSource.setAsyncInit(true);
                druidDataSource.setKillWhenSocketReadTimeout(true);
                druidDataSource.setValidationQuery("select 1;");
                druidDataSource.setKillWhenSocketReadTimeout(true);
                druidDataSource.setConnectionErrorRetryAttempts(10);
                druidDataSource.setNotFullTimeoutRetryCount(10);
                return druidDataSource;
            }, (value1, value2) -> value1));
            final Map<Object, DataSource> resolvedDataSources = dataSourceList.getResolvedDataSources();
            if (dataSourceList.getResolvedDefaultDataSource() == null) {
                dataSourceList.setDefaultTargetDataSource(CollUtil.getLast(resolvedDataSources.values()));
            }
            dataSourceMap.putAll(resolvedDataSources);
            dataSourceList.setTargetDataSources(dataSourceMap);
            dataSourceList.afterPropertiesSet();// 重新解析数据源数量
            connections.clear();
        });
    }

    public boolean setConnections(final DatabaseConnection connections) {
        boolean flag = false;
        try {
            this.connections.add(connections);
            run();
            flag = ObjectUtil.isNotNull(dataSourceList.getConnection());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return flag;
    }
}
