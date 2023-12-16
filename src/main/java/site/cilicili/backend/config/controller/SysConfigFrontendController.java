package site.cilicili.backend.config.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.config.service.SysConfigFrontendService;
import site.cilicili.common.util.R;

/**
 * (SysConfigFrontend) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "config-frontend"})
@Tag(name = "(SysConfigFrontend) 表控制层")
public class SysConfigFrontendController {
    /**
     * 服务对象
     */

    private final SysConfigFrontendService sysConfigFrontendService;

    /**
     * 全查询
     *
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysConfigFrontend 筛选条件")
    })
    @PostMapping({"get-config-frontend-all", "get-config-frontend-list"})
    public R getConfigBackendAll() {
        return this.sysConfigFrontendService.queryConfigFrontAll();
    }
}



