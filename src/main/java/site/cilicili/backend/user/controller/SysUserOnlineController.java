package site.cilicili.backend.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.backend.user.domain.dto.QueryUserOnlineListRequest;
import site.cilicili.backend.user.domain.pojo.SysUserOnlineEntity;
import site.cilicili.backend.user.service.SysUserOnlineService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysUserOnline) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-01-18 20:39:32
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user-online")
@Tag(name = "(SysUserOnline) 表控制层")
public class SysUserOnlineController {
    /**
     * 服务对象
     */
    private final SysUserOnlineService sysUserOnlineService;

    /**
     * 全查询
     *
     * @param sysUserOnline 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysUserOnline 筛选条件")})
    @GetMapping
    public R queryAll(final SysUserOnlineEntity sysUserOnline) {
        return this.sysUserOnlineService.queryAll(sysUserOnline);
    }

    /**
     * 新增数据
     *
     * @param sysUserOnline 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "sysUserOnline 实体")})
    @PostMapping
    public R add(final @RequestBody SysUserOnlineEntity sysUserOnline) {
        return this.sysUserOnlineService.insert(sysUserOnline);
    }

    /**
     * 根据用户名下线用户
     *
     * @param kickOnlineUserRequest 实体
     * @return 新增结果
     */
    @Operation(
            summary = "根据用户名下线用户",
            parameters = {@Parameter(description = "KickOnlineUserRequest 实体")})
    @PostMapping("kick-online-user")
    public R kickOnlineUser(final @RequestBody @Validated KickOnlineUserRequest kickOnlineUserRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysUserOnlineService.kickOnlineUser(kickOnlineUserRequest));
    }

    /**
     * 查询所有在线用户
     *
     * @param queryUserOnlineListRequest 实体
     * @return 新增结果
     */
    @Operation(
            summary = "查询所有在线用户",
            parameters = {@Parameter(description = "sysUserOnline 实体")})
    @PostMapping("get-user-online-list")
    public R getUserOnlineList(
            final @RequestBody @Validated QueryUserOnlineListRequest queryUserOnlineListRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysUserOnlineService.getUserOnlineList(queryUserOnlineListRequest));
    }
}
