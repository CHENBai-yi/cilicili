package site.cilicili.backend.cron.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    /**
     * 动态任务名曾
     */
    private String name;

    /**
     * 设定动态任务开始时间
     */
    private String cron;

    private Long id;
    private String spec;
    private String uuid;
}
