package site.cilicili.frontend.advertising.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.advertising.domain.pojo.VideoAdvertisingEntity;
import site.cilicili.frontend.advertising.service.VideoAdvertisingService;

/**
 * (VideoAdvertising) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("videoAdvertising")
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
    @GetMapping
    public R queryAll(final VideoAdvertisingEntity videoAdvertising) {
        return this.videoAdvertisingService.queryAll(videoAdvertising);
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
        return this.videoAdvertisingService.queryById(id);
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
    public R add(final @RequestBody VideoAdvertisingEntity videoAdvertising) {
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
    @PutMapping
    public R edit(final @RequestBody VideoAdvertisingEntity videoAdvertising) {
        return this.videoAdvertisingService.update(videoAdvertising);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @DeleteMapping
    public R deleteById(final Long id) {
        return this.videoAdvertisingService.deleteById(id);
    }
}
