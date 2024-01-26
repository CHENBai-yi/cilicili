package site.cilicili.backend.cron.service;

import site.cilicili.backend.cron.domain.dto.CronListQueryRequest;
import site.cilicili.backend.cron.domain.dto.Task;
import site.cilicili.common.util.R;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.cron.service
 * Date:2024/1/27 1:21
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
public interface CronService {
    R getCronList(CronListQueryRequest cronListQueryRequest);

    R stopTask(Task task);

    R startTask(Task task);
}
