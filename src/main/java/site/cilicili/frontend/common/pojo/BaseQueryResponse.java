package site.cilicili.frontend.common.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.common.pojo
 * Date:2024/2/27 17:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseQueryResponse {
    protected Object records;
    protected Integer page;
    protected Integer pageSize;
    protected Integer total;
}
