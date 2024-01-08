package site.cilicili.common.mvcConfig;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;

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

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        final String location = Optional.ofNullable(sysConfigBackendService.getBaseMapper().selectOne(new QueryWrapper<SysConfigBackendEntity>().eq(BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(), BackendConfigItem.UPLOADAVATARSAVEPATH.getItem())))
                .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom()).filter(StrUtil::isNotBlank).orElse(sysConfigBackendEntity.getItemDefault())).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
        registry.addResourceHandler(String.format("/%1$s/**", location))
                .addResourceLocations(String.format("file:%1$s\\", location));
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(MultipartProperties multipartProperties) {
        Optional.ofNullable(sysConfigBackendService.getBaseMapper().selectOne(new QueryWrapper<SysConfigBackendEntity>().eq(BackendConfigItem.AVATARMAXSIZE.getKey(), BackendConfigItem.AVATARMAXSIZE.getItem())))
                .ifPresent(avatarMaxsize -> {
                    if (StrUtil.isNotBlank(avatarMaxsize.getItemCustom())) {
                        multipartProperties.setMaxFileSize(DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemCustom()) * 1024 * 1024));
                        multipartProperties.setMaxRequestSize(DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemCustom()) * 1024 * 1024));
                    } else {
                        multipartProperties.setMaxFileSize(DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemDefault()) * 1024 * 1024));
                        multipartProperties.setMaxRequestSize(DataSize.ofBytes(Long.parseLong(avatarMaxsize.getItemDefault()) * 1024 * 1024));
                    }
                });
        return multipartProperties.createMultipartConfig();
    }
}
