package site.cilicili.backend.api.domain.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.api.domain.dto
 * Date:2024/1/14 20:28
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddApiRequest {
    String status;
    @NotNull(message = "排序不能为空")
    @Positive(message = "排序值必须为正数")
    String sort;
    String memo;
    @NotNull(message = "API路径不能为空")
    String apiPath;
    @NotNull(message = "API方法不能为空")
    String apiMethod;
    @NotNull(message = "API分组不能为空")
    String apiGroup;
    @JacksonInject("stable")
    String stable;
}

