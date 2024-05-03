package site.cilicili.frontend.advertising.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.advertising.domain.dto
 * Date:2024/5/2 14:07
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@Accessors(chain = true)
@Schema(description = "VideoAdvertising视图类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoAdvertisingVo {
    @JsonProperty("type")
    private String adverType;
    @JsonProperty("content")
    private String adverContent;
    @Pattern(regexp = "^http://.*?", message = "不是正确的链接地址")
    @JsonProperty("link")
    private String adverLink;
}
