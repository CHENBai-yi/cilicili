package site.cilicili.common.config.dynamicDb.dataSource;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.druid.pool.DruidDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import site.cilicili.common.config.dynamicDb.MyDataSourceList;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.db.domain.mapper.DbSourceToDruidDataSource;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;
import site.cilicili.common.db.service.DatabaseConnectionService;
import site.cilicili.common.util.DbUtils;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Collections;
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
    private final DbChangeConfig dbChangeConf;

    /**
     * @param args incoming main method arguments
     * @descrption 数据库信息配置多数据源
     */
    @Override
    public void run(final String... args) {
        try {
            List<DatabaseConnection> connections1 = databaseConnectionService.list();
            resolveSource(connections1);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public Boolean isValid() {
        return Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner()))
                .isPresent();
    }

    public boolean setConnections(final DatabaseConnection connections) {
        return resolveSource(Collections.singletonList(connections));
    }

    public boolean resolveSource(final List<DatabaseConnection> connections) {
        return Optional.ofNullable(connections)
                .map(databaseConnections -> {
                    try {
                        final Map<Object, Object> dataSourceMap = databaseConnections.stream()
                                .collect(Collectors.toMap(
                                        DatabaseConnection::getScheme,
                                        connection -> {
                                            final DruidDataSource druidDataSource =
                                                    DbSourceToDruidDataSource.DB_SOURCE_TO_DRUID_DATA_SOURCE
                                                            .toDruidDataSource(connection);
                                            try {
                                                druidDataSource.setDriverClassName(connection.getDriver());
                                                druidDataSource.setDefaultAutoCommit(true);
                                                druidDataSource.setAsyncInit(true);
                                                druidDataSource.setKillWhenSocketReadTimeout(true);
                                                druidDataSource.setValidationQuery("select 1;");
                                                druidDataSource.setConnectionErrorRetryAttempts(3);
                                                druidDataSource.setFailFast(true);
                                                druidDataSource.init();
                                            } catch (SQLException e) {
                                                log.error(e.getMessage());
                                                return null;
                                            }
                                            return druidDataSource;
                                        },
                                        (value1, value2) -> value1));
                        if (!dataSourceMap.isEmpty()) {
                            final Map<Object, DataSource> resolvedDataSources = dataSourceList.getResolvedDataSources();
                            dataSourceMap.putAll(resolvedDataSources);
                            dataSourceList.setTargetDataSources(dataSourceMap);
                            dataSourceList.setDefaultTargetDataSource(CollUtil.getFirst(dataSourceMap.values()));
                            dataSourceList.afterPropertiesSet(); // 重新解析数据源数量
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                    return isValid();
                })
                .orElse(false);
    }

    /**
     * 服务器下线前对所有用户强制下线处理
     */
    // @PreDestroy
    // public void preDestroy() {
    //     Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner()))
    //             .filter(r -> sysUserOnlineService.dropOnline())
    //             .ifPresent(r -> {
    //                 log.error("服务器停机，强制下线所有用户");
    //                 appExceptionHandler.handleAppException(new AppException(Error.KICKED_USER));
    //             });
    // }
}
