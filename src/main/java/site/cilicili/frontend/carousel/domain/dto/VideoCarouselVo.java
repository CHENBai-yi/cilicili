package site.cilicili.frontend.carousel.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.carousel.domain.dto
 * Date:2024/5/2 13:48
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Slf4j
@Data
@ToString(doNotUseGetters = true)
@Schema(description = "VideoCarousel展示类")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VideoCarouselVo {
    @JsonProperty("img")
    private String imageUrl;

    private String backgroundColor;
    private String link;
}
