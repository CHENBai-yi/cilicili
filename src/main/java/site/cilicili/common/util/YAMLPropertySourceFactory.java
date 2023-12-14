package site.cilicili.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.dbUtils
 * Date:2023/12/6 1:41
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public class YAMLPropertySourceFactory implements PropertySourceFactory {
    private static final String YML = ".yml";
    private static final String YAML = ".yaml";
    private final Logger logger = LoggerFactory.getLogger(YAMLPropertySourceFactory.class);

    /**
     * @param name：@PropertySource 注解 name 的值
     * @param resource：资源
     */
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        // 文件名称
        String filename = resource.getResource().getFilename();
        // 属性源的名称
        String resourceName = Optional.ofNullable(name).orElse(filename);
        try {
            if (filename != null) {
                if (filename.endsWith(YML) || filename.endsWith(YAML)) {
                    List<PropertySource<?>> yamlSources = new YamlPropertySourceLoader().load(resourceName, new FileUrlResource(((ClassPathResource) resource.getResource()).getPath()));
                    return yamlSources.get(0);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return new DefaultPropertySourceFactory().createPropertySource(name, resource);
    }

}

