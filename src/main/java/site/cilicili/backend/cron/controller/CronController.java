package site.cilicili.backend.cron.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.cron.domain.dto.CronListQueryRequest;
import site.cilicili.backend.cron.domain.dto.Task;
import site.cilicili.backend.cron.service.CronService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.cron.controller
 * Date:2024/1/27 1:20
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("cron")
@Tag(name = "(cron) 表控制层")
public class CronController {
    public final CronService cronService;

    /**
     * 查询计划任务列表
     *
     * @param cronListQueryRequest 查询条件
     * @return 计划任务列表
     */
    @Operation(
            summary = "查询计划任务列表",
            parameters = {@Parameter(description = "CronListQueryRequest 实体")})
    @PostMapping("get-cron-list")
    public R getCronList(final @RequestBody CronListQueryRequest cronListQueryRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(cronService.getCronList(cronListQueryRequest));
    }

    /**
     * 开启一个动态任务
     *
     * @param task
     * @return
     */
    @PostMapping("start-cron")
    public R startDynamicTask(@RequestBody Task task) {
        return cronService.startTask(task);
    }

    /**
     * 根据名称 停止一个动态任务
     *
     * @param task
     * @return
     */
    @PostMapping("stop-cron")
    public R stopDynamicTask(@RequestBody Task task) {
        return cronService.stopTask(task);
    }
}
