package site.cilicili.backend.upAndown.upload.service;

import org.springframework.web.multipart.MultipartFile;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.upAndown.upload.service
 * Date:2024/1/7 22:24
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public interface UploadService {
    R uploadUserAvatar(AuthUserDetails authUserDetails, MultipartFile[] multipartFile);

    R uploadBannerImage(AuthUserDetails authUserDetails, MultipartFile[] multipartFile);

    R uploadLogo(AuthUserDetails authUserDetails, MultipartFile[] multipartFile);

    R uploadFavicon(AuthUserDetails authUserDetails, MultipartFile[] multipartFile);

    R saveUploadedFiles(MultipartFile uploadfile);
}
