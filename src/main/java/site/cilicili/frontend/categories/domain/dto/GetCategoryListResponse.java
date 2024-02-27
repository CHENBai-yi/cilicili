package site.cilicili.frontend.categories.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.cilicili.frontend.common.pojo.BaseQueryResponse;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.categories.domain.dto
 * Date:2024/2/27 21:01
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data

@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetCategoryListResponse extends BaseQueryResponse {
    @Builder
    public GetCategoryListResponse(final Object records, final Integer page, final Integer pageSize, final Integer total) {
        super(records, page, pageSize, total);
    }
}

