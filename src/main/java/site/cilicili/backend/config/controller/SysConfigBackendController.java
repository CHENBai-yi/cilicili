package site.cilicili.backend.config.controller;

import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedBackendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * (SysConfigBackend) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:43
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "config-backend"})
@Tag(name = "(SysConfigBackend) 表控制层")
public class SysConfigBackendController {
    /**
     * 服务对象
     */
    private final SysConfigBackendService sysConfigBackendService;

    /**
     * 全查询
     *
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"get-config-backend-all"})
    public R getConfigBackendAll() {
        return this.sysConfigBackendService.queryConfigBackendAll();
    }

    /**
     * 条件查询
     *
     * @return 查询结果
     */
    @Operation(
            summary = "条件查询",
            parameters = {@Parameter(description = "sysConfigBack 筛选条件")})
    @PostMapping({"get-config-backend-list"})
    public R getConfigBackendList(@RequestBody @Validated QueryConfigRequest queryBackRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysConfigBackendService.getConfigBackendList(queryBackRequest));
    }

    /**
     * 添加后台配置
     *
     * @return 查询结果
     */
    @Operation(
            summary = "添加前台配置",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"config-backend-add"})
    public R configBackendAdd(@RequestBody @Validated AddConfigRequest addConfigRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysConfigBackendService.configBackendAdd(addConfigRequest));
    }

    /**
     * 修改后台配置
     *
     * @return 查询结果
     */
    @Operation(
            summary = "修改前台配置",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"edit-config-backend"})
    public R editConfigBackend(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            @RequestBody @Validated EditedBackendConfigRequest editedBackendConfigRequest,
            Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysConfigBackendService.editConfigBackend(authUserDetails, editedBackendConfigRequest));
    }
}
