package site.cilicili.backend.config.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.config.domain.dto
 * Date:2024/1/17 16:43
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EditedFrontendConfigRequest extends SysConfigFrontendEntity {
    @NotNull(message = "id不允许为空")
    @Positive(message = "id必须大于零")
    private Long id;
}
