package site.cilicili.backend.config.domain.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.config.domain.dto
 * Date:2024/1/17 16:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddConfigRequest {

    @NotNull(message = "配置项不能为空")
    String configItem;
    @NotNull(message = "默认配置项不能为空")
    String itemDefault;
    String memo;
    @JacksonInject("status")
    String status;
    @JacksonInject("table")
    String table;
    @NotNull(message = "排序不能为空") @Positive(message = "排序不能为负数")
    Integer sort;
}
