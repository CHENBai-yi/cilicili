package site.cilicili.frontend.carousel.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import site.cilicili.frontend.common.pojo.BaseQueryResponse;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.carousel.domain.dto
 * Date:2024/4/29 15:41
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class QueryCarouselResponse extends BaseQueryResponse {
    @Builder
    public QueryCarouselResponse(
            final Object records, final Integer page, final Integer pageSize, final Integer total) {
        super(records, page, pageSize, total);
    }
}
