package site.cilicili.backend.log.controller;

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
import site.cilicili.backend.log.domain.dto.DeleteLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.service.SysLogLoginService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysLogLogin) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:43
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("log")
@Tag(name = "(SysLogLogin) 表控制层")
public class SysLogLoginController {
    /**
     * 服务对象
     */

    private final SysLogLoginService sysLogLoginService;

    /**
     * 根据条件查询登录日志
     *
     * @param queryLogRequest 查询条件
     * @return 响应结果
     */
    @Operation(summary = "根据条件查询登录日志", parameters = {
            @Parameter(description = "id 主键")
    })
    @PostMapping("get-log-login-list")
    public R getLogLoginList(@RequestBody @Validated QueryLogRequest queryLogRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.sysLogLoginService.getLogLoginList(queryLogRequest));
    }

    /**
     * 根据id删除登录日志
     *
     * @param deleteLogRequest 查询条件
     * @return 删除是否成功
     */
    @Operation(summary = "根据id删除登录日志", parameters = {
            @Parameter(description = "id 主键")
    })
    @PostMapping("delete-log-login-by-id")
    public R deleteLogLoginById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated DeleteLogRequest deleteLogRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining())))
                .orElse(this.sysLogLoginService.deleteLogLoginById(authUserDetails, deleteLogRequest));
    }
}



