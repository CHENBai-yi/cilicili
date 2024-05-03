package site.cilicili.frontend.carousel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.service.SseEmitterService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselDto;
import site.cilicili.frontend.carousel.service.VideoCarouselService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (VideoCarousel) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:46
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "carousel"})
@Tag(name = "(VideoCarousel) 表控制层")
public class VideoCarouselController {
    /**
     * 服务对象
     */
    private final VideoCarouselService videoCarouselService;

    /**
     * 全查询
     *
     * @param videoCarousel 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "videoCarousel 筛选条件")})
    @PostMapping("list")
    public R queryAll(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.queryAll(videoCarousel);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param videoCarousel 主键
     * @return 单条数据
     */
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("query-carousel-by-id")
    public R queryById(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.queryById(videoCarousel.getId());
    }

    /**
     * 刷新轮播图时间,按时间返回轮播图
     *
     * @return R
     */
    private final SseEmitterService carouselService;

    /**
     * 编辑数据
     *
     * @param videoCarousel 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "videoCarouselEntity 实体")})
    @PostMapping("edit-carousel")
    public R edit(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.update(videoCarousel);
    }

    /**
     * 删除数据
     *
     * @param videoCarousel 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("delete-carousel-by-id")
    public R deleteById(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.deleteById(videoCarousel);
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
    public R changeStatus(final @RequestBody VideoCarouselDto status) {
        return this.videoCarouselService.update(status);
    }

    /**
     * 新增数据
     *
     * @param videoCarousel 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "videoCarousel 实体")})
    @PostMapping
    public R add(final @RequestBody @Validated VideoCarouselDto videoCarousel, Errors exception) throws BindException {
        if (exception.hasFieldErrors()) {
            throw new AppException(exception.getFieldErrors().stream()
                    .map(item ->
                            Optional.ofNullable(item.getDefaultMessage()).orElse(Error.COMMON_EXCEPTION.getMessage()))
                    .collect(Collectors.joining(",")));
        }
        return this.videoCarouselService.insert(videoCarousel);
    }

    /**
     * 按时间返回轮播图
     *
     * @return R
     */
    @Operation(summary = "按时间返回轮播图")
    @PostMapping("get-carousel-list")
    public R videoCarouselListByTime() {
        return this.videoCarouselService.getCarouselList();
    }

    @Operation(summary = "刷新轮播图时间,按时间返回轮播图")
    @PostMapping("flush-carousel-list")
    public R flushCarouselList() {
        return carouselService.flushCarouselList();
    }

    @Operation(summary = "刷新轮播图时间,按时间返回轮播图")
    @GetMapping("subscribe-carousel")
    public SseEmitter subscribeCarousel() {
        return carouselService.subscribeCarousel();
    }
}
