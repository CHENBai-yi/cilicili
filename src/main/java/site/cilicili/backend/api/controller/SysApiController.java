package site.cilicili.backend.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.api.domain.dto.GetApiListRequest;
import site.cilicili.backend.api.service.SysApiService;
import site.cilicili.common.util.R;

/**
 * (SysApi) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:43
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Tag(name = "(SysApi) 表控制层")
public class SysApiController {
    /**
     * 服务对象
     */

    private final SysApiService sysApiService;

    /**
     * 查api列表
     *
     * @param apiListRequest 筛选条件
     * @return 查询结果
     */
    @Operation(summary = "全查询", parameters = {
            @Parameter(description = "sysButton 筛选条件")
    })
    @PostMapping("get-api-list")
    public R getApiList(@RequestBody final GetApiListRequest apiListRequest) {
        return sysApiService.getApiList(apiListRequest);
    }
}



