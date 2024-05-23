package site.cilicili.backend.chart.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.chart.service.ChartBackendService;
import site.cilicili.common.util.R;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"monitor", "public"})
@Tag(name = "(chart) 表控制层")
/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.chart
 * Date:2024/1/27 19:39
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public class ChartBackendController {
    private final ChartBackendService chartService;

    @PostMapping("get-cili-data-backend")
    public R getInfo() {
        return chartService.getCiliDataBoard();
    }

    @GetMapping("command")
    public R getRedisInfo() {
        return chartService.getInfo();
    }
}
