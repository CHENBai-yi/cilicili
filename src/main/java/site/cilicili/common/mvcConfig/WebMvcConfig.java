package site.cilicili.common.mvcConfig;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.AbstractResourceResolver;
import org.springframework.web.servlet.resource.ResourceResolverChain;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;

import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.webConfig
 * Date:2024/1/7 22:08
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Configuration
@RequiredArgsConstructor
@Getter
public class WebMvcConfig implements WebMvcConfigurer {
    private final SysConfigBackendService sysConfigBackendService;
    private final ObjectMapper objectMapper;
    private final DbChangeConfig dbChangeConf;
    private final DbInitialization dbInitialization;

    @Value("${requestPath.avatar:avatar}")
    private String avatarRequestPath;

    @Value("${requestPath.avatarPrefix:gqa-upload:}")
    private String avatarPrefix;

    @PostConstruct
    public void postInit() {
        // 序列化的时候序列对象的所有属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);

        // 反序列化的时候如果多了其他属性,不抛出异常
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 如果是空对象的时候,不抛异常
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 取消时间的转化格式,默认是时间戳,可以取消,同时需要设置要表现的时间格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        final InjectableValues.Std std = new InjectableValues.Std();
        std.addValue("status", "onOff_on");
        std.addValue("stable", "yesNo_no");
        std.addValue("deptDataPermissionType", "deptDataPermissionType_user");
        std.addValue("defaultPage", "dashboard");
        std.addValue("sort", 1L);
        objectMapper.setInjectableValues(std);
    }

    // @Override
    // public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    //     Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner())).ifPresent(it -> {
    //         final String location = Optional.ofNullable(sysConfigBackendService
    //                         .getBaseMapper()
    //                         .selectOne(new QueryWrapper<SysConfigBackendEntity>()
    //                                 .eq(
    //                                         BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(),
    //                                         BackendConfigItem.UPLOADAVATARSAVEPATH.getItem())))
    //                 .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
    //                         .filter(StrUtil::isNotBlank)
    //                         .orElse(sysConfigBackendEntity.getItemDefault()))
    //                 .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    //         registry.addResourceHandler(String.format("/%1$s/**", location))
    //                 .addResourceLocations(String.format("file:%1$s/", location));
    //     });
    //     WebMvcConfigurer.super.addResourceHandlers(registry);
    // }
    private String getAvatarSavePathFromDatabase() {
        // 从数据库中获取头像保存路径配置
        return Optional.ofNullable(dbInitialization.isValid())
                .filter(f -> f)
                .map(f -> sysConfigBackendService
                        .getBaseMapper()
                        .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                .eq(
                                        BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(),
                                        BackendConfigItem.UPLOADAVATARSAVEPATH.getItem())))
                .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                        .filter(StrUtil::isNotBlank)
                        .orElse(sysConfigBackendEntity.getItemDefault()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(String.format("/%1$s/**", avatarRequestPath).trim())
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic())
                .resourceChain(true)
                .addResolver(new AbstractResourceResolver() {
                    @Override
                    protected Resource resolveResourceInternal(
                            final HttpServletRequest request,
                            final String requestPath,
                            final List<? extends Resource> locations,
                            final ResourceResolverChain chain) {
                        try {
                            final String s = resolveUrlPathInternal(requestPath, locations, chain);
                            // 检查资源是否存在，避免抛出异常
                            return Optional.of(new UrlResource(s))
                                    .filter(resource -> resource.exists() && resource.isReadable())
                                    .orElse(null); // 返回null表示资源不存在，可以让PathResourceResolver使用默认的处理方式
                        } catch (Exception e) {
                            if (logger.isDebugEnabled()) {
                                logger.error(e.getMessage());
                            }
                        }
                        return null;
                    }

                    @Override
                    protected String resolveUrlPathInternal(
                            final String resourceUrlPath,
                            final List<? extends Resource> locations,
                            final ResourceResolverChain chain) {
                        String avatarSavePath = getAvatarSavePathFromDatabase();
                        Path requestedPath = Path.of(avatarSavePath, resourceUrlPath);
                        return requestedPath.toUri().toString();
                    }
                });
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
        return Optional.ofNullable(dbInitialization.isValid())
                .filter(f -> !f)
                .map(f -> multipartProperties.createMultipartConfig())
                .orElseGet(() -> new CiliMultipartConfigElement(
                        multipartProperties.getLocation(),
                        multipartProperties.getMaxFileSize(),
                        multipartProperties.getMaxRequestSize(),
                        multipartProperties.getFileSizeThreshold(),
                        sysConfigBackendService));
    }

    /*@Bean
    public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
        Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner()))
                .flatMap(it -> Optional.ofNullable(sysConfigBackendService
                        .getBaseMapper()
                        .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                .eq(
                                        BackendConfigItem.AVATARMAXSIZE.getKey(),
                                        BackendConfigItem.AVATARMAXSIZE.getItem()))))
                .ifPresent(avatarMaxsize -> {
                    if (StrUtil.isNotBlank(avatarMaxsize.getItemCustom())) {
                        multipartProperties.setMaxFileSize(
                                DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemCustom()) * 1024 * 1024));
                        multipartProperties.setMaxRequestSize(
                                DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemCustom()) * 1024 * 1024));
                    } else {
                        multipartProperties.setMaxFileSize(
                                DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemDefault()) * 1024 * 1024));
                        multipartProperties.setMaxRequestSize(
                                DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemDefault()) * 1024 * 1024));
                    }
                });

        return multipartProperties.createMultipartConfig();
    }*/
}
