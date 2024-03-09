package site.cilicili.frontend.subjects.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import site.cilicili.frontend.common.pojo.BaseQueryResponse;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.subjects.domain.dto
 * Date:2024/2/27 17:25
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetSubjectListResponse extends BaseQueryResponse {
    @Builder
    public GetSubjectListResponse(
            final Object records, final Integer page, final Integer pageSize, final Integer total) {
        super(records, page, pageSize, total);
    }
}
