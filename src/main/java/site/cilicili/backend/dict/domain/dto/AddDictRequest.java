package site.cilicili.backend.dict.domain.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dict.domain.dto
 * Date:2024/1/14 19:55
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddDictRequest {
    @NotNull String dictCode;
    @NotNull String dictLabel;
    @JacksonInject("stable")
    String stable;
    String memo;
    String sort;
    String status;
    String parentCode;
}
