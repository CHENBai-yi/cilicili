package site.cilicili.frontend.chart.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.common.util.R;
import site.cilicili.frontend.chart.service.ChartService;

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
public class ChartController {
    private final ChartService chartService;

    @PostMapping("get-cili-data-fronted")
    public R getCiliDataBoard() {
        return chartService.getCiliDataBoard();
    }
}
