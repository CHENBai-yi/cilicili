package site.cilicili.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.cilicili.backend.domain.pojo.SysNoticeToUser;
import site.cilicili.backend.service.SysNoticeToUserService;
import site.cilicili.common.util.R;

/**
 * (SysNoticeToUser)表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("sysNoticeToUser")
@Tag(name = "(SysNoticeToUser)表控制层")
public class SysNoticeToUserController {
    /**
     * 服务对象
     */

    private final SysNoticeToUserService sysNoticeToUserService;

    /**
     * 全查询
     *
     * @param sysNoticeToUser 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysNoticeToUser 筛选条件")
    })
    @GetMapping
    public R queryAll(final SysNoticeToUser sysNoticeToUser) {
        return this.sysNoticeToUserService.queryAll(sysNoticeToUser);
    }

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实体
     * @return 新增结果
     */

    @Operation(summary = "新增数据", parameters = {
            @Parameter(description = "sysNoticeToUser 实体")
    })
    @PostMapping
    public R add(final @RequestBody SysNoticeToUser sysNoticeToUser) {
        return this.sysNoticeToUserService.insert(sysNoticeToUser);
    }

    /**
     * 编辑数据
     *
     * @param sysNoticeToUser 实体
     * @return 编辑结果
     */
    @Operation(summary = "编辑数据", parameters = {
            @Parameter(description = "sysNoticeToUser 实体")
    })
    @PutMapping
    public R edit(final @RequestBody SysNoticeToUser sysNoticeToUser) {
        return this.sysNoticeToUserService.update(sysNoticeToUser);
    }


}



