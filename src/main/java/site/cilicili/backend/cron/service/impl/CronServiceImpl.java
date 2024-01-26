package site.cilicili.backend.cron.service.impl;

import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import site.cilicili.backend.cron.domain.dto.CronListQueryRequest;
import site.cilicili.backend.cron.domain.dto.Task;
import site.cilicili.backend.cron.service.CronService;
import site.cilicili.backend.notice.controller.SysNoticeController;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;
import site.cilicili.common.util.R;
import site.cilicili.common.websocket.CiliMessage;
import site.cilicili.common.websocket.WebSocketEndpoint;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledFuture;

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
public class CronServiceImpl implements CronService {
    private static Long NUMBER = 0L;
    private final ThreadPoolTaskScheduler syncScheduler;
    private final SysNoticeController sysNoticeController;
    private final WebSocketEndpoint webSocketEndpoint;
    /**
     * 以下两个都是线程安全的集合类。
     */
    public Map<String, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>();

    public List<String> taskList = new CopyOnWriteArrayList<String>();
    public Map<String, Task> taskLists = new ConcurrentHashMap<>();

    @Autowired
    public CronServiceImpl(
            final ThreadPoolTaskScheduler syncScheduler,
            final SysNoticeController sysNoticeController,
            final WebSocketEndpoint webSocketEndpoint) {
        this.syncScheduler = syncScheduler;
        this.sysNoticeController = sysNoticeController;
        this.webSocketEndpoint = webSocketEndpoint;
        final Task task = new Task();
        task.setName("早上8:30推送通知");
        task.setStart(
                LocalDateTime.now().withHour(8).withMinute(30).withSecond(0).withNano(0));
        task.setId(NUMBER);
        task.setUuid(IdUtil.fastUUID());
        task.setSpec("@every 8:30");
        taskLists.put(task.getUuid(), task);
    }

    private static long getDelayToNextExecution(int hour, int minute) {
        long current = System.currentTimeMillis();
        long now = current % (24 * 60 * 60 * 1000);
        long target = hour * 60 * 60 * 1000 + minute * 60 * 1000;
        long delay = target - now;
        if (delay < 0) {
            delay += 24 * 60 * 60 * 1000;
        }
        return delay;
    }

    /**
     * 查看已开启但还未执行的动态任务
     *
     * @return
     */
    public List<String> getTaskList() {
        return taskList;
    }

    public List<Task> getTaskLists() {
        return taskLists.values().stream().toList();
    }

    /**
     * 添加一个动态任务
     *
     * @param task
     * @return
     */
    public boolean add(Task task) {
        // 此处的逻辑是 ，如果当前已经有这个名字的任务存在，先删除之前的，再添加现在的。（即重复就覆盖）
        if (null != taskMap.get(task.getUuid())) {
            stop(task.getUuid());
        }
        if (taskLists.containsKey(task.getUuid())) {
            task = taskLists.get(task.getUuid());
            task.setId(++NUMBER);
        }
        // hutool 工具包下的一个转换类型工具类 好用的很
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        Date startTime = converterRegistry.convert(Date.class, task.getStart());

        // schedule :调度给定的Runnable ，在指定的执行时间调用它。
        // 一旦调度程序关闭或返回的ScheduledFuture被取消，执行将结束。
        // 参数：
        // 任务 – 触发器触发时执行的 Runnable
        // startTime – 任务所需的执行时间（如果这是过去，则任务将立即执行，即尽快执行）
        ScheduledFuture<?> schedule = syncScheduler.schedule(
                getRunnable(task), new Date(System.currentTimeMillis() + getDelayToNextExecution(8, 30)));
        taskMap.put(task.getUuid(), schedule);
        return true;
    }

    /**
     * 运行任务
     *
     * @param task
     * @return
     */
    public Runnable getRunnable(Task task) {
        return () -> {
            log.info("---动态定时任务运行---");
            try {
                System.out.println("此时时间==>" + LocalDateTime.now());
                final SysNoticeEntity sysNotice = new SysNoticeEntity();
                sysNotice.setNoticeId(IdUtil.fastUUID());
                sysNotice.setNoticeContent(
                        "早上好！今天是：" + DateUtil.format(DateUtil.date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
                sysNotice.setNoticeTitle("温馨提示");
                sysNotice.setNoticeType("noticeType_system");
                sysNotice.setNoticeToUserType("all");
                sysNotice.setNoticeSent("yesNo_yes");
                sysNoticeController.add(sysNotice);
                webSocketEndpoint.sendAllMessage(CiliMessage.builder()
                        .text(sysNotice.getNoticeContent())
                        .messageType(sysNotice.getNoticeType())
                        .build());
                System.out.println("task中设定的时间==>" + task);
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("---end--------");
        };
    }

    /**
     * 停止任务
     *
     * @param name
     * @return
     */
    public boolean stop(String name) {
        if (null == taskMap.get(name)) {
            return false;
        }
        ScheduledFuture<?> scheduledFuture = taskMap.get(name);
        scheduledFuture.cancel(true);
        taskMap.remove(name);
        for (final Task task : taskLists.values()) {
            if (task.getUuid().equals(name)) {
                task.setId(0L);
            }
        }
        return true;
    }

    @Override
    public R getCronList(final CronListQueryRequest cronListQueryRequest) {
        return R.yes("Success.").setRecords(getTaskLists());
    }

    @Override
    public R stopTask(final Task task) {
        // 将这个添加到动态定时任务中去
        if (!stop(task.getUuid())) {
            return R.no("停止失败,任务已在进行中.");
        }
        return R.yes("任务已停止");
    }

    @Override
    public R startTask(final Task task) {
        if (add(task)) {
            return R.yes("动态任务:" + task.getUuid() + " 已开启");
        }
        return R.no("任务启动失败！");
    }
}
