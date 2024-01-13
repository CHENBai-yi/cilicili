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
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.web.servlet.MultipartProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.config.dynamicDb.annotation.DbChangeConfig;
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.DbUtils;

import java.text.SimpleDateFormat;
import java.util.Optional;

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
public class WebMvcConfig implements WebMvcConfigurer {
    private final SysConfigBackendService sysConfigBackendService;
    private final ObjectMapper objectMapper;
    private final DbChangeConfig dbChangeConf;

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
        objectMapper.setInjectableValues(std);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner()))
                .ifPresent(it -> {
                    final String location = Optional.ofNullable(sysConfigBackendService
                                    .getBaseMapper()
                                    .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                            .eq(
                                                    BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(),
                                                    BackendConfigItem.UPLOADAVATARSAVEPATH.getItem())))
                            .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                                    .filter(StrUtil::isNotBlank)
                                    .orElse(sysConfigBackendEntity.getItemDefault()))
                            .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
                    registry.addResourceHandler(String.format("/%1$s/**", location))
                            .addResourceLocations(String.format("file:%1$s/", location));
                });
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
        Optional.ofNullable(DbUtils.checkDb(dbChangeConf.getBackendInner())).flatMap(it -> Optional.ofNullable(sysConfigBackendService
                .getBaseMapper()
                .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                        .eq(
                                BackendConfigItem.AVATARMAXSIZE.getKey(),
                                BackendConfigItem.AVATARMAXSIZE.getItem())))).ifPresent(avatarMaxsize -> {
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
    }
}
