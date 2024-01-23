package site.cilicili.common.mvcConfig;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.util.unit.DataSize;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;

import java.util.Objects;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.mvcConfig
 * Date:2024/1/22 10:39
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public class CiliMultipartConfigElement extends MultipartConfigElement {
    public static final Long UNIT = 1024 * 1024L;
    private final SysConfigBackendService sysConfigBackendService;

    public CiliMultipartConfigElement(
            final String location,
            final DataSize maxFileSize,
            final DataSize maxRequestSize,
            final DataSize fileSizeThreshold,
            final SysConfigBackendService sysConfigBackendService) {
        super(
                location,
                maxFileSize.toBytes(),
                maxRequestSize.toBytes(),
                Integer.parseInt("" + fileSizeThreshold.toBytes()));
        this.sysConfigBackendService = sysConfigBackendService;
    }

    @Override
    public long getMaxFileSize() {
        return getLongOptional().orElse(super.getMaxFileSize());
    }

    @Override
    public long getMaxRequestSize() {
        return getLongOptional().orElse(super.getMaxRequestSize());
    }

    private Optional<Long> getLongOptional() {
        return Optional.ofNullable(sysConfigBackendService
                        .getBaseMapper()
                        .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                .eq(
                                        BackendConfigItem.AVATARMAXSIZE.getKey(),
                                        BackendConfigItem.AVATARMAXSIZE.getItem())))
                .map(sysConfigBackendEntity -> {
                    if (Objects.nonNull(sysConfigBackendEntity.getItemCustom())) {
                        return Long.parseLong(sysConfigBackendEntity.getItemCustom()) * UNIT;
                    } else {
                        return Long.parseLong(sysConfigBackendEntity.getItemDefault()) * UNIT;
                    }
                });
    }
}
