package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysRoleApi;
import site.cilicili.backend.service.SysRoleApiService;
import site.cilicili.common.util.R;

/**
 * (SysRoleApi)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysRoleApi")
@Tag(name = "(SysRoleApi)表控制层")
public class SysRoleApiController {
    /**
     * 服务对象
     */

    private final SysRoleApiService sysRoleApiService;

    /**
     * 全查询
     *
     * @param sysRoleApi 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysRoleApi 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysRoleApi sysRoleApi) {
        return this.sysRoleApiService.queryAll(sysRoleApi);
    }

    /**
     * 新增数据
     *
     * @param sysRoleApi 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysRoleApi 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysRoleApi sysRoleApi) {
        return this.sysRoleApiService.insert(sysRoleApi);
    }

    /**
     * 编辑数据
     *
     * @param sysRoleApi 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysRoleApi 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysRoleApi sysRoleApi) {
        return this.sysRoleApiService.update(sysRoleApi);
    }


}



