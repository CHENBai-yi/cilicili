package site.cilicili.backend.upAndown.upload.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.backend.config.service.SysConfigFrontendService;
import site.cilicili.backend.upAndown.upload.service.UploadService;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.constant.ConfigBackend.BackendConfigItem;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.mvcConfig.WebMvcConfig;
import site.cilicili.common.util.R;

import java.io.File;
import java.io.IOException;
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
@Slf4j
public class UploadServiceImpl implements UploadService {
    private final SysUserService sysUserService;
    private final SysConfigBackendService sysConfigBackendService;
    private final WebMvcConfig webMvcConfig;
    private final SysConfigFrontendService sysConfigFrontendService;

    /**
     * 修改当前用户的头像
     *
     * @param authUserDetails
     * @param multipartFile
     * @return
     */
    @Transactional(rollbackFor = Throwable.class)
    public R uploadUserAvatars(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return getMultipartImageFilesCommon(
                multipartFile, BackendConfigItem.AVATAREXT.getItem(), BackendConfigItem.AVATARMAXSIZE.getItem())
                .map(multipartFiles -> Optional.ofNullable(
                                sysUserService.getBaseMapper().selectById(authUserDetails.getId()))
                        .map(userEntity -> {
                            final List<String> list1 = Arrays.stream(multipartFiles)
                                    .map(item -> {
                                        final String fileName = getBasePath(item);
                                        return Optional.ofNullable(getStorePath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        BackendConfigItem.UPLOADAVATARSAVEPATH.getItem(),
                                                        webMvcConfig.getAvatarRequestPath(),
                                                        fileName,
                                                        authUserDetails.getUsername(),
                                                        userEntity.getAvatar()))
                                                .filter(path -> writtenImage(item, path))
                                                .map(path -> getAvatarPath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        webMvcConfig.getAvatarRequestPath(),
                                                        authUserDetails.getUsername(),
                                                        fileName))
                                                .map(avatar -> {
                                                    userEntity.setAvatar(avatar);
                                                    Optional.of(sysUserService.saveOrUpdate(userEntity))
                                                            .filter(f -> f)
                                                            .orElseThrow(
                                                                    () -> new AppException(Error.COMMON_EXCEPTION));
                                                    return avatar;
                                                })
                                                .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
                                    })
                                    .toList();
                            return Optional.of(list1)
                                    .filter(list -> list1.size() == 1)
                                    .map(list -> R.yes("Success").setRecords(list.get(0)))
                                    .orElse(R.yes("Success").setRecords(list1));
                        })
                        .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION)))
                .orElseThrow(() -> new AppException(Error.UPLOAD_FAIL));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R uploadUserAvatar(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return getMultipartImageFilesCommon(
                multipartFile, BackendConfigItem.AVATAREXT.getItem(), BackendConfigItem.AVATARMAXSIZE.getItem())
                .map(multipartFiles -> {
                    final List<String> list1 = Arrays.stream(multipartFiles)
                            .map(item -> {
                                final String fileName = getBasePath(item);
                                return Optional.ofNullable(getStorePath(
                                                webMvcConfig.getCommonPrefix(),
                                                BackendConfigItem.UPLOADAVATARSAVEPATH.getItem(),
                                                webMvcConfig.getAvatarRequestPath(),
                                                fileName,
                                                BackendConfigItem.UPLOADAVATARSAVEPATH.getFrontedItem(),
                                                null))
                                        .filter(path -> writtenImage(item, path))
                                        .map(path -> getAvatarPath(
                                                webMvcConfig.getCommonPrefix(),
                                                webMvcConfig.getAvatarRequestPath(),
                                                BackendConfigItem.UPLOADAVATARSAVEPATH.getFrontedItem(),
                                                fileName))
                                        .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
                            })
                            .toList();
                    return Optional.of(list1)
                            .filter(list -> list1.size() == 1)
                            .map(list -> R.yes("Success").setRecords(list.get(0)))
                            .orElse(R.yes("Success").setRecords(list1));
                })
                .orElseThrow(() -> new AppException(Error.UPLOAD_FAIL));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R uploadBannerImage(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return getMultipartImageFilesCommon(multipartFile, BackendConfigItem.BANNERIMAGEEXT.getItem(), null)
                .map(multipartFiles -> Optional.ofNullable(sysConfigFrontendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigFrontendEntity>()
                                        .eq(
                                                BackendConfigItem.UPLOADBANNERIMAGESAVEPATH.getKey(),
                                                BackendConfigItem.UPLOADBANNERIMAGESAVEPATH.getFrontedItem())))
                        .map(entity -> {
                            final List<String> list1 = Arrays.stream(multipartFiles)
                                    .map(item -> {
                                        final String fileName = getBasePath(item);
                                        return Optional.ofNullable(getStorePath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        BackendConfigItem.UPLOADBANNERIMAGESAVEPATH.getItem(),
                                                        webMvcConfig.getBannerRequestPath(),
                                                        fileName,
                                                        BackendConfigItem.UPLOADBANNERIMAGESAVEPATH.getFrontedItem(),
                                                        entity.getItemCustom()))
                                                .filter(path -> writtenImage(item, path))
                                                .map(path -> getAvatarPath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        webMvcConfig.getBannerRequestPath(),
                                                        BackendConfigItem.UPLOADBANNERIMAGESAVEPATH.getFrontedItem(),
                                                        fileName))
                                                .map(banner -> {
                                                    entity.setItemCustom(banner);
                                                    Optional.of(sysConfigFrontendService.saveOrUpdate(entity))
                                                            .filter(f -> f)
                                                            .orElseThrow(
                                                                    () -> new AppException(Error.COMMON_EXCEPTION));
                                                    return banner;
                                                })
                                                .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
                                    })
                                    .toList();
                            return Optional.of(list1)
                                    .filter(list -> list1.size() == 1)
                                    .map(list -> R.yes("Success").setRecords(list.get(0)))
                                    .orElse(R.yes("Success").setRecords(list1));
                        })
                        .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION)))
                .orElseThrow(() -> new AppException(Error.UPLOAD_FAIL));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R uploadLogo(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return getMultipartImageFilesCommon(
                multipartFile, BackendConfigItem.LOGOEXT.getItem(), BackendConfigItem.LOGOMAXSIZE.getItem())
                .map(multipartFiles -> Optional.ofNullable(sysConfigFrontendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigFrontendEntity>()
                                        .eq(
                                                BackendConfigItem.UPLOADLOGOSAVEPATH.getKey(),
                                                BackendConfigItem.UPLOADLOGOSAVEPATH.getFrontedItem())))
                        .map(entity -> {
                            final List<String> list1 = Arrays.stream(multipartFiles)
                                    .map(item -> {
                                        final String fileName = getBasePath(item);
                                        return Optional.ofNullable(getStorePath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        BackendConfigItem.UPLOADLOGOSAVEPATH.getItem(),
                                                        webMvcConfig.getLogoRequestPath(),
                                                        fileName,
                                                        BackendConfigItem.UPLOADLOGOSAVEPATH.getFrontedItem(),
                                                        entity.getItemCustom()))
                                                .filter(path -> writtenImage(item, path))
                                                .map(path -> getAvatarPath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        webMvcConfig.getLogoRequestPath(),
                                                        BackendConfigItem.UPLOADLOGOSAVEPATH.getFrontedItem(),
                                                        fileName))
                                                .map(logo -> {
                                                    entity.setItemCustom(logo);
                                                    Optional.of(sysConfigFrontendService.saveOrUpdate(entity))
                                                            .filter(f -> f)
                                                            .orElseThrow(
                                                                    () -> new AppException(Error.COMMON_EXCEPTION));
                                                    return logo;
                                                })
                                                .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
                                    })
                                    .toList();
                            return Optional.of(list1)
                                    .filter(list -> list1.size() == 1)
                                    .map(list -> R.yes("Success").setRecords(list.get(0)))
                                    .orElse(R.yes("Success").setRecords(list1));
                        })
                        .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION)))
                .orElseThrow(() -> new AppException(Error.UPLOAD_FAIL));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R uploadFavicon(final AuthUserDetails authUserDetails, final MultipartFile[] multipartFile) {
        return getMultipartImageFilesCommon(
                multipartFile,
                BackendConfigItem.FAVICONEXT.getItem(),
                BackendConfigItem.FAVICONMAXSIZE.getItem())
                .map(multipartFiles -> Optional.ofNullable(sysConfigFrontendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigFrontendEntity>()
                                        .eq(
                                                BackendConfigItem.UPLOADFAVICONSAVEPATH.getKey(),
                                                BackendConfigItem.UPLOADFAVICONSAVEPATH.getFrontedItem())))
                        .map(entity -> {
                            final List<String> list1 = Arrays.stream(multipartFiles)
                                    .map(item -> {
                                        final String fileName = getBasePath(item);
                                        return Optional.ofNullable(getStorePath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        BackendConfigItem.UPLOADFAVICONSAVEPATH.getItem(),
                                                        webMvcConfig.getFaviconRequestPath(),
                                                        fileName,
                                                        BackendConfigItem.UPLOADFAVICONSAVEPATH.getFrontedItem(),
                                                        entity.getItemCustom()))
                                                .filter(path -> writtenImage(item, path))
                                                .map(path -> getAvatarPath(
                                                        webMvcConfig.getCommonPrefix(),
                                                        webMvcConfig.getFaviconRequestPath(),
                                                        BackendConfigItem.UPLOADFAVICONSAVEPATH.getFrontedItem(),
                                                        fileName))
                                                .map(logo -> {
                                                    entity.setItemCustom(logo);
                                                    Optional.of(sysConfigFrontendService.saveOrUpdate(entity))
                                                            .filter(f -> f)
                                                            .orElseThrow(
                                                                    () -> new AppException(Error.COMMON_EXCEPTION));
                                                    return logo;
                                                })
                                                .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW));
                                    })
                                    .toList();
                            return Optional.of(list1)
                                    .filter(list -> list1.size() == 1)
                                    .map(list -> R.yes("Success").setRecords(list.get(0)))
                                    .orElse(R.yes("Success").setRecords(list1));
                        })
                        .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION)))
                .orElseThrow(() -> new AppException(Error.UPLOAD_FAIL));
    }

    @Override
    public R saveUploadedFiles(final MultipartFile uploadfile) {
        return Optional.ofNullable(saveUploadedVideoFiles(uploadfile))
                .map(path -> R.yes("上传成功").setRecords(path))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    private Optional<MultipartFile[]> getMultipartImageFilesCommon(
            final MultipartFile[] multipartFile, String ext, String size) {
        return Optional.ofNullable(multipartFile)
                .filter(multipartFiles -> multipartFiles.length > 0)
                .filter(multipartFiles -> Optional.ofNullable(ext)
                        .map(e -> sysConfigBackendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                        .eq(BackendConfigItem.AVATAREXT.getKey(), e)))
                        .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                                .filter(StrUtil::isNotBlank)
                                .orElse(sysConfigBackendEntity.getItemDefault()))
                        .map(suffix -> Optional.of(multipartFiles.length
                                        == Arrays.stream(multipartFiles)
                                        .filter(item -> suffix.startsWith(
                                                Objects.requireNonNull(item.getOriginalFilename())
                                                        .substring(item.getOriginalFilename()
                                                                .indexOf("."))
                                                        .toLowerCase(Locale.ROOT))
                                                || suffix.endsWith(item.getOriginalFilename()
                                                .substring(item.getOriginalFilename()
                                                        .indexOf("."))
                                                .toLowerCase(Locale.ROOT)))
                                        .count())
                                .orElseThrow(() -> new AppException(Error.AVATAREXT_DISALLOW)))
                        .orElse(true))
                .filter(multipartFiles -> Optional.ofNullable(size)
                        .map(s -> sysConfigBackendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                        .eq(BackendConfigItem.AVATAREXT.getKey(), s)))
                        .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                                .filter(StrUtil::isNotBlank)
                                .orElse(sysConfigBackendEntity.getItemDefault()))
                        .map(s -> WebMvcConfig.UNIT * Long.parseLong(s))
                        .map(s -> Optional.of(multipartFiles.length
                                        == Arrays.stream(multipartFiles)
                                        .filter(item -> item.getSize() <= s)
                                        .count())
                                .filter(f -> f)
                                .orElseThrow(() -> new AppException(Error.FILE_EXECEED)))
                        .orElse(true));
    }

    private Boolean writtenImage(final MultipartFile item, final String storePath) {
        try {
            ClassPathResource resource = new ClassPathResource(storePath);
            if (resource.exists()) {
                FileUtil.writeFromStream(item.getInputStream(), resource.getFile(), true);
            } else {
                FileUtil.writeFromStream(item.getInputStream(), new File(resource.getPath()), true);
            }
            return true;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    private String getAvatarPath(String prefix, String path, String username, String fileName) {
        return String.format("%1$s%2$s/%3$s/%4$s", prefix, path, username, fileName)
                .trim();
    }

    private String getStorePath(
            String prefix,
            String backendItem,
            String requestPath,
            String fileName,
            String username,
            String originPath) {
        return Optional.ofNullable(sysConfigBackendService
                        .getBaseMapper()
                        .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                .eq(BackendConfigItem.UPLOADAVATARSAVEPATH.getKey(), backendItem)))
                .map(sysConfigBackendEntity -> Optional.ofNullable(sysConfigBackendEntity.getItemCustom())
                        .filter(StrUtil::isNotBlank)
                        .orElse(sysConfigBackendEntity.getItemDefault()))
                .map(location -> {
                    Optional.ofNullable(originPath)
                            .filter(StrUtil::isNotBlank)
                            .flatMap(av -> Optional.of(new File(location
                                            + av.replace(
                                            String.format("%1$s%2$s", prefix, requestPath)
                                                    .trim(),
                                            "")))
                                    .filter(File::exists))
                            .ifPresent(FileUtil::del);
                    return String.format("%1$s/%2$s/%3$s", location, username, fileName);
                })
                .orElse(null);
    }

    private String getBasePath(MultipartFile item) {
        return String.format(
                        "%1$s_%2$s%3$s",
                        IdUtil.fastSimpleUUID(),
                        LocalDateTimeUtil.format(LocalDateTime.now(), "yyyyMMddHHmmssSSS"),
                        Objects.requireNonNull(item.getOriginalFilename())
                                .substring(item.getOriginalFilename().indexOf(".")))
                .trim();
    }

    private String saveUploadedVideoFiles(final MultipartFile file) {
        final String storePath = String.format("%1$s/%2$s", "video", file.getOriginalFilename());
        if (writtenImage(file, storePath)) {
            return storePath;
        }
        return null;
    }
}
