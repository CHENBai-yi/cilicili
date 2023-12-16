package site.cilicili.backend.config.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.util.R;

/**
 * (SysConfigBackend) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:43
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "config-backend"})
@Tag(name = "(SysConfigBackend) 表控制层")
public class SysConfigBackendController {
    /**
     * 服务对象
     */

    private final SysConfigBackendService sysConfigBackendService;

    /**
     * 全查询
     *
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysConfigFrontend 筛选条件")
    })
    @PostMapping({"get-config-backend-all", "get-config-backend-list"})
    public R getConfigBackendAll() {
        return this.sysConfigBackendService.queryConfigBackendAll();
    }
}



