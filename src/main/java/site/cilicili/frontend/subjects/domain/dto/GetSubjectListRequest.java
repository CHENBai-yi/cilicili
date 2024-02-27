package site.cilicili.frontend.subjects.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.subjects.domain.dto
 * Date:2024/2/27 17:22
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class GetSubjectListRequest {
    private Integer id;
    private Boolean desc;
    private Integer page;
    private Integer pageSize;
    private String subjectCode;
    private String subjectName;
    private String sortBy;
}
