package site.cilicili.backend.cron.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.cron.domain.dto
 * Date:2024/1/27 1:33
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@Accessors(chain = true) // 方便链式编写 习惯所然
public class Task {
    /**
     * 动态任务名曾
     */
    private String name;

    /**
     * 设定动态任务开始时间
     */
    private LocalDateTime start;

    private Long id;
    private String spec;
    private String uuid;
}
