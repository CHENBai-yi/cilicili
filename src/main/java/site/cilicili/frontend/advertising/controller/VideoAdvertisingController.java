package site.cilicili.frontend.advertising.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.advertising.domain.dto.VideoAdvertisingDto;
import site.cilicili.frontend.advertising.service.VideoAdvertisingService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (VideoAdvertising) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "advertising"})
@Tag(name = "(VideoAdvertising) 表控制层")
public class VideoAdvertisingController {
    /**
     * 服务对象
     */
    private final VideoAdvertisingService videoAdvertisingService;

    /**
     * 全查询
     *
     * @param videoAdvertising 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "videoAdvertising 筛选条件")})
    @PostMapping("list")
    public R queryAll(final @RequestBody VideoAdvertisingDto videoAdvertising) {
        return this.videoAdvertisingService.queryAll(videoAdvertising);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param videoAdvertising 主键
     * @return 单条数据
     */
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("query-advertising-by-id")
    public R queryById(final @RequestBody VideoAdvertisingDto videoAdvertising) {
        return this.videoAdvertisingService.queryById(videoAdvertising);
    }

    /**
     * 新增数据
     *
     * @param videoAdvertising 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "videoAdvertising 实体")})
    @PostMapping
    public R add(final @RequestBody @Validated VideoAdvertisingDto videoAdvertising, Errors exception) {
        if (exception.hasFieldErrors()) {
            throw new AppException(exception.getFieldErrors().stream()
                    .map(item ->
                            Optional.ofNullable(item.getDefaultMessage()).orElse(Error.COMMON_EXCEPTION.getMessage()))
                    .collect(Collectors.joining(",")));
        }
        return this.videoAdvertisingService.insert(videoAdvertising);
    }

    /**
     * 编辑数据
     *
     * @param videoAdvertising 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "videoAdvertisingEntity 实体")})
    @PostMapping("edit-advertising")
    public R edit(final @RequestBody VideoAdvertisingDto videoAdvertising) {
        return this.videoAdvertisingService.update(videoAdvertising);
    }

    /**
     * 删除数据
     *
     * @param videoAdvertising 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("delete-advertising-by-id")
    public R deleteById(final @RequestBody VideoAdvertisingDto videoAdvertising) {
        return this.videoAdvertisingService.deleteById(videoAdvertising);
    }

    /**
     * 修改状态
     *
     * @param status 状态属性
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("change-status")
    public R changeStatus(final @RequestBody VideoAdvertisingDto status) {
        return this.videoAdvertisingService.update(status);
    }

    /**
     * 按时间返回广告图
     *
     * @return R
     */
    @Operation(
            summary = "按时间返回广告图")
    @PostMapping("get-adver-list")
    public R videoCarouselListByTime() {
        return this.videoAdvertisingService.getAdverList();
    }
}
