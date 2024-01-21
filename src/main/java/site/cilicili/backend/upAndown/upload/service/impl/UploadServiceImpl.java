package site.cilicili.backend.upAndown.upload.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.backend.upAndown.upload.service.UploadService;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.mvcConfig.WebMvcConfig;
import site.cilicili.common.util.R;

import java.io.File;
import java.time.LocalDateTime;
import java.util.*;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.upAndown.upload.service.impl
 * Date:2024/1/7 22:24
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Service
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {
    private final SysUserService sysUserService;
    private final SysConfigBackendService sysConfigBackendService;
    private final WebMvcConfig webMvcConfig;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R uploadUserAvatar(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return Optional.ofNullable(multipartFile).filter(multipartFiles -> multipartFiles.length > 0).filter(multipartFiles -> {
            final String suffix = Optional.ofNullable(sysConfigBackendService.getBaseMapper().selectOne(new QueryWrapper<SysConfigBackendEntity>().eq(BackendConfigItem.AVATAREXT.getKey(), BackendConfigItem.AVATAREXT.getItem()))).map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom()).filter(StrUtil::isNotBlank).orElse(sysConfigBackendEntity.getItemDefault())).orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
            return multipartFiles.length == Arrays.stream(multipartFiles).filter(item -> suffix.startsWith(Objects.requireNonNull(item.getOriginalFilename()).substring(item.getOriginalFilename().indexOf(".")).toLowerCase(Locale.ROOT)) || suffix.endsWith(item.getOriginalFilename().substring(item.getOriginalFilename().indexOf(".")).toLowerCase(Locale.ROOT))).count();
        }).map(multipartFiles -> Optional.ofNullable(sysUserService.getBaseMapper().selectById(authUserDetails.getId())).map(userEntity -> {
            final List<String> list1 = Arrays.stream(multipartFiles).map(item -> {
                try {
                    final String fileName = getBasePath(item);
                    ClassPathResource resource = new ClassPathResource(getStorePath(webMvcConfig.getAvatarPrefix(), webMvcConfig.getAvatarRequestPath(), fileName, authUserDetails.getUsername(), userEntity));
                    if (resource.exists()) {
                        FileUtil.writeFromStream(item.getInputStream(), resource.getFile(), true);
                    } else {
                        FileUtil.writeFromStream(item.getInputStream(), new File(resource.getPath()), true);
                    }
                    final String avatar = getAvatarPath(webMvcConfig.getAvatarPrefix(), webMvcConfig.getAvatarRequestPath(), authUserDetails.getUsername(), fileName);
                    userEntity.setAvatar(avatar);
                    sysUserService.saveOrUpdate(userEntity);
                    return avatar;
                } catch (Exception e) {
                    throw new AppException(Error.COMMON_EXCEPTION);
                }
            }).toList();
            return Optional.of(list1).filter(list -> list1.size() == 1).map(list -> R.yes("Success").setRecords(list.get(0))).orElse(R.yes("Success").setRecords(list1));
        }).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION))).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    private String getAvatarPath(String prefix, String path, String username, String fileName) {
        return String.format("%1$s%2$s/%3$s/%4$s", prefix, path, username, fileName).trim();
    }

    private String getStorePath(String prefix, String requestPath, String fileName, String username, SysUserEntity userEntity) {
        return Optional.ofNullable(sysConfigBackendService.getBaseMapper().selectOne(new QueryWrapper<SysConfigBackendEntity>()
                        .eq(BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(), BackendConfigItem.UPLOADAVATARSAVEPATH.getItem())))
                .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                        .filter(StrUtil::isNotBlank)
                        .orElse(sysConfigBackendEntity.getItemDefault()))
                .map(location -> {
                    Optional.ofNullable(userEntity.getAvatar())
                            .filter(StrUtil::isNotBlank)
                            .flatMap(av ->
                                    Optional.of(new File(location + av.replace(String.format("%1$s%2$s", prefix, requestPath).trim(), "")))
                                            .filter(File::exists)).ifPresent(FileUtil::del);
                    return String.format("%1$s/%2$s/%3$s", location, username, fileName);
                }).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));

    }

    private String getBasePath(MultipartFile item) {
        return String.format("%1$s_%2$s%3$s", IdUtil.fastSimpleUUID(), LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmssSSS"), Objects.requireNonNull(item.getOriginalFilename()).substring(item.getOriginalFilename().indexOf("."))).trim();
    }
}
