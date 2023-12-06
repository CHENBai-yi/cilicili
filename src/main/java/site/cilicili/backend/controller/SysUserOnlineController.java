package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysUserOnline;
import site.cilicili.backend.service.SysUserOnlineService;
import site.cilicili.common.util.R;

/**
 * (SysUserOnline)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysUserOnline")
@Tag(name = "(SysUserOnline)表控制层")
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
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysUserOnline 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysUserOnline sysUserOnline) {
        return this.sysUserOnlineService.queryAll(sysUserOnline);
    }

    /**
     * 新增数据
     *
     * @param sysUserOnline 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysUserOnline 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysUserOnline sysUserOnline) {
        return this.sysUserOnlineService.insert(sysUserOnline);
    }

    /**
     * 编辑数据
     *
     * @param sysUserOnline 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysUserOnline 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysUserOnline sysUserOnline) {
        return this.sysUserOnlineService.update(sysUserOnline);
    }


}



