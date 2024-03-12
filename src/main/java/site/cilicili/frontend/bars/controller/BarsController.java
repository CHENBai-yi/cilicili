package site.cilicili.frontend.bars.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.bars.service.BarsService;

/**
 * (Bars) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:41
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("bars")
@Tag(name = "(Bars) 表控制层")
public class BarsController {
    /**
     * 服务对象
     */
    private final BarsService barsService;

    /**
     * 全查询
     *
     * @param bars 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "bars 筛选条件")})
    @GetMapping
    public R queryAll(final BarsEntity bars) {
        return this.barsService.queryAll(bars);
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
    public R queryById(final @PathVariable("id") Integer id) {
        return this.barsService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param bars 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "bars 实体")})
    @PostMapping
    public R add(final @RequestBody BarsEntity bars) {
        return this.barsService.insert(bars);
    }


    /**
     * 编辑数据
     *
     * @param bars 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "barsEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody BarsEntity bars) {
        return this.barsService.update(bars);
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
    public R deleteById(final Integer id) {
        return this.barsService.deleteById(id);
    }

    @PostMapping("update-video-url")
    public R updateVideoUrl(final @RequestBody BarsEntity bars) {
        return this.barsService.updateVideoUrl(bars);
    }
}
