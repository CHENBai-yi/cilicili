package site.cilicili.frontend.carousel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.common.util.R;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselDto;
import site.cilicili.frontend.carousel.service.VideoCarouselService;

/**
 * (VideoCarousel) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:46
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("carousel")
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
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "videoCarousel 筛选条件")
    })
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
    @Operation(summary = "通过主键查询单条数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @PostMapping("query-carousel-by-id")
    public R queryById(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.queryById(videoCarousel.getId());
    }

    /**
     * 新增数据
     *
     * @param videoCarousel 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "videoCarousel 实体")
    })
    @PostMapping
    public R add(final @RequestBody @Validated VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.insert(videoCarousel);
    }

    /**
     * 编辑数据
     *
     * @param videoCarousel 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "videoCarouselEntity 实体")
    })
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
    @Operation(summary = "删除数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @PostMapping("delete-carousel-by-id")
    public R deleteById(final @RequestBody VideoCarouselDto videoCarousel) {
        return this.videoCarouselService.deleteById(videoCarousel);
    }
}



