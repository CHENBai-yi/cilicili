package site.cilicili.backend.config.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedFrontendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.service.SysConfigFrontendService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysConfigFrontend) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "config-frontend"})
@Tag(name = "(SysConfigFrontend) 表控制层")
public class SysConfigFrontendController {
    /**
     * 服务对象
     */
    private final SysConfigFrontendService sysConfigFrontendService;

    /**
     * 全查询
     *
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"get-config-frontend-all"})
    public R getConfigFrontendAll() {
        return this.sysConfigFrontendService.queryConfigFrontAll();
    }

    /**
     * 条件查询
     *
     * @return 查询结果
     */
    @Operation(
            summary = "条件查询",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"get-config-frontend-list"})
    public R getConfigFrontendList(@RequestBody @Validated QueryConfigRequest queryFrontendRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.sysConfigFrontendService.getConfigFrontendList(queryFrontendRequest));
    }

    /**
     * 添加前台配置
     *
     * @return 查询结果
     */
    @Operation(
            summary = "添加前台配置",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"config-frontend-add"})
    public R configFrontendAdd(@RequestBody @Validated AddConfigRequest addConfigRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.sysConfigFrontendService.configFrontendAdd(addConfigRequest));
    }

    /**
     * 修改前台配置
     *
     * @return 查询结果
     */
    @Operation(
            summary = "修改前台配置",
            parameters = {@Parameter(description = "sysConfigFrontend 筛选条件")})
    @PostMapping({"edit-config-frontend"})
    public R editConfigFrontend(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated EditedFrontendConfigRequest editedFrontendConfigRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.sysConfigFrontendService.editConfigFrontend(authUserDetails, editedFrontendConfigRequest));
    }
}
