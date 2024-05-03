package site.cilicili.backend.notice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryRequest;
import site.cilicili.backend.notice.domain.dto.QueryNoticeReadRequest;
import site.cilicili.backend.notice.domain.dto.SendNoticeRequest;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;
import site.cilicili.backend.notice.service.SysNoticeService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysNotice) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:21
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("notice")
@Tag(name = "(SysNotice) 表控制层")
public class SysNoticeController {
    /**
     * 服务对象
     */
    private final SysNoticeService sysNoticeService;

    /**
     * 全查询
     *
     * @param sysNotice 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysNotice 筛选条件")})
    @GetMapping
    public R queryAll(final SysNoticeEntity sysNotice) {
        return this.sysNoticeService.queryAll(sysNotice);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
    @GetMapping("{id}")
    public R queryById(final @PathVariable("id") Long id) {
        return this.sysNoticeService.queryById(id);
    }

    /**
     * 新增消息
     *
     * @param sysNotice 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增消息",
            parameters = {@Parameter(description = "sysNotice 实体")})
    @PostMapping("add-notice")
    public R add(final @RequestBody SysNoticeEntity sysNotice) {
        return this.sysNoticeService.insert(sysNotice);
    }

    /**
     * 编辑数据
     *
     * @param sysNotice 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysNoticeEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody SysNoticeEntity sysNotice) {
        return this.sysNoticeService.update(sysNotice);
    }

    /**
     * 删除数据
     *
     * @param sysNotice 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("delete-notice-by-id")
    public R deleteById(
            @AuthenticationPrincipal AuthUserDetails authUserDetails, final @RequestBody SysNoticeEntity sysNotice) {
        return this.sysNoticeService.deleteById(authUserDetails, sysNotice);
    }

    /**
     * 根据id查询消息
     *
     * @param queryNoticeReadRequest 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "根据id查询消息",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("query-notice-read-by-id")
    public R queryNoticeReadById(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            final @RequestBody QueryNoticeReadRequest queryNoticeReadRequest,
            Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysNoticeService.queryNoticeReadById(authUserDetails, queryNoticeReadRequest));
    }

    /**
     * 发送通知
     *
     * @param sendNoticeRequest 消息
     * @return 发送是否成功
     */
    @Operation(
            summary = "发送通知",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("send-notice")
    public R sendNotice(
            @AuthenticationPrincipal AuthUserDetails authUserDetails,
            final @RequestBody @Validated SendNoticeRequest sendNoticeRequest,
            Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysNoticeService.sendNotice(authUserDetails, sendNoticeRequest));
    }

    /**
     * 按条件查询通知列表
     *
     * @param noticeListQueryRequest 查询条件
     * @return 通知列表
     */
    @Operation(
            summary = "按条件查询通知列表",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("get-notice-list")
    public R getNoticeList(final @RequestBody @Validated NoticeListQueryRequest noticeListQueryRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysNoticeService.getNoticeList(noticeListQueryRequest));
    }

    /**
     * 按条件查询通知列表
     *
     * @param noticeListQueryRequest 查询条件
     * @return 通知列表
     */
    @Operation(
            summary = "按条件查询通知列表",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("get-notice-count")
    public R getNoticeListCount(
            final @RequestBody @Validated NoticeListQueryRequest noticeListQueryRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysNoticeService.getNoticeListCount(noticeListQueryRequest));
    }
}
