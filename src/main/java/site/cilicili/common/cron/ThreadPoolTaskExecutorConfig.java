package site.cilicili.common.cron;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.cron
 * Date:2024/1/27 1:34
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Configuration
public class ThreadPoolTaskExecutorConfig {

    @Bean
    public ThreadPoolTaskScheduler syncScheduler() {
        ThreadPoolTaskScheduler syncScheduler = new ThreadPoolTaskScheduler();
        syncScheduler.setPoolSize(5);
        // 这里给线程设置名字，主要是为了在项目能够更快速的定位错误。
        syncScheduler.setThreadGroupName("cili-syncTg");
        syncScheduler.setThreadNamePrefix("cili-syncThread-");
        syncScheduler.setAwaitTerminationSeconds(30);
        syncScheduler.setRemoveOnCancelPolicy(true);
        syncScheduler.setWaitForTasksToCompleteOnShutdown(true);

        syncScheduler.initialize();
        return syncScheduler;
    }
}
