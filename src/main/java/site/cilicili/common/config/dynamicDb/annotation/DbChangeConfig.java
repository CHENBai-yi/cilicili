package site.cilicili.common.config.dynamicDb.annotation;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import site.cilicili.common.util.YAMLPropertySourceFactory;

/**
 * @author BaiYiChen
 */
@Component
@Data
@Primary
@PropertySource(
        value = "classpath:dbConfig.yaml",
        factory = YAMLPropertySourceFactory.class,
        ignoreResourceNotFound = true,
        encoding = "utf8")
public class DbChangeConfig {
    @Value("${backend.scheme:backend}")
    private String backend;

    @Value("${spring.datasource.master.scheme:backend}")
    private String backendInner;

    @Value("${frontend.scheme:frontend}")
    private String frontend;

    @Value("${spring.datasource.slave.scheme:frontend}")
    private String frontendInner;
}
