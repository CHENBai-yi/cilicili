package site.cilicili.common.swagger;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
@Configuration
@EnableKnife4j
@ConditionalOnProperty(
        value = {"knife4j.enable"},
        matchIfMissing = true)
@ConfigurationProperties(prefix = "knife4j.config")
@Data
public class SwaggerConfig {
    private String frontbasePackage = "com.example.swagger.user";
    private String backendPackage = "com.example.swagger.admin";
    private String frontGroupName = "前台API分组";
    private String backendGroupName = "后台API分组";
    private String title = "swagger-bootstrap-ui RESTful APIs";
    private String description = "swagger-bootstrap-ui";
    private String termsOfServiceUrl = "http://localhost:8999/";
    private String version = "1.0";
    private String name = "ChenBaiYi";
    private String url = "mailto:chenbaiyi.work@gmail.com?subject=好的建议与遇到的问题&body=说说你遇到的问题";
    private String email = "chenbaiyi.work@gmail.com";
    private String licenseName = "Apache 2.0";
    private String licenseUrl = "http://springdoc.org";
    private String externalDescription = "外部文档";
    private String externalUrl = "https://springshop.wiki.github.org/docs";

    @Bean
    public OpenAPI springSwaggerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .termsOfService(termsOfServiceUrl)
                        .contact(new Contact().name(name).url(url).email(email))
                        .license(new License().name(licenseName).url(licenseUrl)))
                .externalDocs(new ExternalDocumentation()
                        .description(externalDescription)
                        .url(externalUrl));
    }

    @SecurityScheme(type = SecuritySchemeType.HTTP, name = "JWT", scheme = "bearer", in = SecuritySchemeIn.HEADER)
    public class Swagger3Config {
    }
}
