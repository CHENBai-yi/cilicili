package site.cilicili;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import site.cilicili.common.config.dynamicDb.MyDataSourceList;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.dbUtils.DbUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili
 * Date:2023/12/4 21:18
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@RequiredArgsConstructor
@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class, SecurityAutoConfiguration.class, H2ConsoleAutoConfiguration.class})
public class App {
    private final DbChangeConfig dbChangeConf;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 数据库类型是MySql，因此参数填写DbType.MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @Primary
    public MyDataSourceList myDataSourceList(@Qualifier("backend") DruidDataSource druidDataSource) {
        final HashMap<Object, Object> dataSourceMap = new HashMap<>();
        DataSource defaultDataSource = null;
        Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner())).ifPresent(databaseConnectionDto1 -> {
            druidDataSource.setPassword(databaseConnectionDto1.getDbPassword());
            druidDataSource.setUsername(databaseConnectionDto1.getDbUser());
            druidDataSource.setUrl(String.join("/", databaseConnectionDto1.getUrl(), databaseConnectionDto1.getScheme()));
            druidDataSource.setDriverClassName(databaseConnectionDto1.getDriver());
            druidDataSource.setDefaultAutoCommit(true);
            druidDataSource.setAsyncInit(true);
            druidDataSource.setKillWhenSocketReadTimeout(true);
            druidDataSource.setValidationQuery("select 1;");
            druidDataSource.setKillWhenSocketReadTimeout(true);
            druidDataSource.setConnectionErrorRetryAttempts(10);
            druidDataSource.setNotFullTimeoutRetryCount(10);
            if (StrUtil.isNotBlank(dbChangeConf.getBackend())) {
                dataSourceMap.put(dbChangeConf.getBackend(), druidDataSource);
            } else if (StrUtil.isNotBlank(dbChangeConf.getBackendInner())) {
                dataSourceMap.put(dbChangeConf.getBackendInner(), druidDataSource);
            }
        });
        if (!dataSourceMap.isEmpty()) {
            defaultDataSource = (DataSource) CollUtil.getLast(dataSourceMap.values());
        }
        return new MyDataSourceList(defaultDataSource, dataSourceMap);
    }

    @Bean("backend")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DruidDataSource mySql() {
        return new DruidDataSource();
    }


}
