package site.cilicili.backend.upAndown.upload.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.upAndown.upload.service.UploadService;
import site.cilicili.common.util.R;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.upAndown.upload.controller
 * Date:2024/1/7 19:43
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final UploadService uploadService;

    @PostMapping("upload/upload-avatar")
    public R uploadUserAvatar(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestPart(name = "file") MultipartFile[] multipartFile) {
        return uploadService.uploadUserAvatar(authUserDetails, multipartFile);
    }
}
