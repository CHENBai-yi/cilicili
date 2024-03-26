package site.cilicili.frontend.course.domain.dto;

import lombok.Data;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.course.domain.dto
 * Date:2024/3/26 23:46
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class GetSubjectCategoriesRequest {
    private String subject;
    private String kind;
}
