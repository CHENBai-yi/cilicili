package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysLogOperation;
import site.cilicili.backend.service.SysLogOperationService;
import site.cilicili.common.util.R;

/**
 * (SysLogOperation)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:42
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysLogOperation")
@Tag(name = "(SysLogOperation)表控制层")
public class SysLogOperationController {
    /**
     * 服务对象
     */

    private final SysLogOperationService sysLogOperationService;

    /**
     * 全查询
     *
     * @param sysLogOperation 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysLogOperation 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysLogOperation sysLogOperation) {
        return this.sysLogOperationService.queryAll(sysLogOperation);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(summary = "通过主键查询单条数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @GetMapping("{id}")
    public R queryById(final @PathVariable("id") Long id) {
        return this.sysLogOperationService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysLogOperation 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysLogOperation 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysLogOperation sysLogOperation) {
        return this.sysLogOperationService.insert(sysLogOperation);
    }

    /**
     * 编辑数据
     *
     * @param sysLogOperation 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysLogOperation 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysLogOperation sysLogOperation) {
        return this.sysLogOperationService.update(sysLogOperation);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(summary = "删除数据", parameters = {
            @Parameter(description = "id 主键")
    })
    @DeleteMapping
    public R deleteById(final Long id) {
        return this.sysLogOperationService.deleteById(id);
    }
}



