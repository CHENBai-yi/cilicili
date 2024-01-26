package site.cilicili.common.cron;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;
import site.cilicili.backend.notice.service.SysNoticeService;
import site.cilicili.common.websocket.CiliMessage;
import site.cilicili.common.websocket.WebSocketEndpoint;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.cron
 * Date:2024/1/27 16:33
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Component
@Data
@Slf4j
public class CustomerTaskPool {
    private static SysNoticeService SysNoticeService;
    private static WebSocketEndpoint webSocketEndpoint;
    private final ThreadPoolTaskScheduler syncScheduler;
    public Map<String, ScheduledFuture<?>> taskMap = new ConcurrentHashMap<>();

    @Autowired
    public CustomerTaskPool(final ThreadPoolTaskScheduler syncScheduler, final SysNoticeService SysNoticeService, final WebSocketEndpoint webSocketEndpoint) {
        this.syncScheduler = syncScheduler;
        CustomerTaskPool.webSocketEndpoint = webSocketEndpoint;
        CustomerTaskPool.SysNoticeService = SysNoticeService;
    }

    public boolean stop(String name) {
        if (null == taskMap.get(name)) {
            return false;
        }
        final TaskEnum taskEnum = CollUtil.getFirst(Arrays.stream(TaskEnum.values()).filter(item -> item.uuid.equals(name)).toList());
        ScheduledFuture<?> scheduledFuture = taskMap.get(taskEnum.uuid);
        scheduledFuture.cancel(true);
        taskEnum.isRun = false;
        taskMap.remove(taskEnum.uuid);
        return true;
    }

    public boolean add(String uuid) {
        // 此处的逻辑是 ，如果当前已经有这个名字的任务存在，先删除之前的，再添加现在的。（即重复就覆盖）
        if (null != taskMap.get(uuid)) {
            stop(uuid);
        }
        // schedule :调度给定的Runnable ，在指定的执行时间调用它。
        // 一旦调度程序关闭或返回的ScheduledFuture被取消，执行将结束。
        // 参数：
        // 任务 – 触发器触发时执行的 Runnable
        // startTime – 任务所需的执行时间（如果这是过去，则任务将立即执行，即尽快执行）
        final TaskEnum taskEnum = CollUtil.getFirst(Arrays.stream(TaskEnum.values()).filter(item -> item.uuid.equals(uuid)).toList());
        ScheduledFuture<?> schedule = syncScheduler.schedule(taskEnum, new CronTrigger(taskEnum.cron));
        taskEnum.isRun = true;
        taskMap.put(uuid, schedule);
        return true;
    }

    @Getter
    @AllArgsConstructor
    public enum TaskEnum implements Runnable {

        PUT_MESSAGE("早上8:30推送通知", "0 30 8 * * ?", "@every 8:30", IdUtil.fastUUID(), 0L, false) {
            private static Long NUMBER = 0L;

            /**
             *
             */
            @Override
            public void run() {
                log.info("---动态定时任务运行---");
                System.out.println("此时时间==>" + LocalDateTime.now());
                final SysNoticeEntity sysNotice = new SysNoticeEntity();
                sysNotice.setNoticeId(getUuid());
                sysNotice.setNoticeContent(
                        "早上好！今天是：" + DateUtil.format(DateUtil.date(), DatePattern.CHINESE_DATE_TIME_PATTERN));
                sysNotice.setNoticeTitle("早上好！");
                sysNotice.setNoticeType("noticeType_system");
                sysNotice.setNoticeToUserType("all");
                sysNotice.setNoticeSent("yesNo_yes");
                SysNoticeService.insert(sysNotice);
                webSocketEndpoint.sendAllMessage(CiliMessage.builder()
                        .text(sysNotice.getNoticeContent())
                        .messageType(sysNotice.getNoticeType())
                        .build());
                System.out.println("task中设定的时间==>" + getCron());
                log.info("---end--------");
            }

            /**
             * @return
             */
            @Override
            public Long getId() {
                if (isRun) {
                    return ++NUMBER;
                } else {
                    return PUT_MESSAGE.id;
                }
            }
        };
        /**
         * 动态任务名曾
         */
        private final String name;
        /**
         * 设定动态任务开始时间
         */
        private final String cron;
        private final String spec;
        private final String uuid;
        private final Long id;
        public Boolean isRun;
    }
}
