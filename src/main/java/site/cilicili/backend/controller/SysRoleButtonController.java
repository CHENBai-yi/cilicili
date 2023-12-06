package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysRoleButton;
import site.cilicili.backend.service.SysRoleButtonService;
import site.cilicili.common.util.R;

/**
 * (SysRoleButton)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysRoleButton")
@Tag(name = "(SysRoleButton)表控制层")
public class SysRoleButtonController {
    /**
     * 服务对象
     */

    private final SysRoleButtonService sysRoleButtonService;

    /**
     * 全查询
     *
     * @param sysRoleButton 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysRoleButton 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysRoleButton sysRoleButton) {
        return this.sysRoleButtonService.queryAll(sysRoleButton);
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
    public R queryById(final @PathVariable("id") String id) {
        return this.sysRoleButtonService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param sysRoleButton 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysRoleButton 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysRoleButton sysRoleButton) {
        return this.sysRoleButtonService.insert(sysRoleButton);
    }

    /**
     * 编辑数据
     *
     * @param sysRoleButton 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysRoleButton 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysRoleButton sysRoleButton) {
        return this.sysRoleButtonService.update(sysRoleButton);
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
    public R deleteById(final String id) {
        return this.sysRoleButtonService.deleteById(id);
    }
}



