package site.cilicili.common.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.entity
 * Date:2024/5/16 23:30
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@ConfigurationProperties(prefix = "cilicili.role")
@Component
@Data
public class Role {
    private String student;
    private String teacher;
}
