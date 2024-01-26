package site.cilicili.backend.cron.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.cilicili.backend.cron.domain.dto.CronListQueryRequest;
import site.cilicili.backend.cron.domain.dto.Task;
import site.cilicili.backend.cron.service.CronService;
import site.cilicili.common.cron.CustomerTaskPool;
import site.cilicili.common.util.R;

import java.util.Arrays;
import java.util.Optional;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.cron.service
 * Date:2024/1/27 1:21
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CronServiceImpl implements CronService {
    private final CustomerTaskPool customerTaskPool;

    @Override
    public R getCronList(final CronListQueryRequest cronListQueryRequest) {
        return R.yes("Success.").setRecords(BeanUtil.copyToList(Arrays.stream(CustomerTaskPool.TaskEnum.values()).toList(), Task.class));
    }

    @Override
    public R stopTask(final Task task) {
        return Optional.of(customerTaskPool.stop(task.getUuid()))
                .filter(f -> f)
                .map(r -> R.yes("任务已终止运行."))
                .orElseGet(() -> R.no("任务未运行!"));
    }

    @Override
    public R startTask(final Task task) {
        return Optional.of(customerTaskPool.add(task.getUuid()))
                .filter(f -> f)
                .map(r -> R.yes("动态任务:" + task.getUuid() + " 已开启"))
                .orElseGet(() -> R.no("任务正在运行中！"));
    }


}
