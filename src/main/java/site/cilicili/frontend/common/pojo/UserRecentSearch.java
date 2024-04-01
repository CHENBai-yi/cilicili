package site.cilicili.frontend.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.frontend.common.pojo
 * Date:2024/3/31 15:27
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserRecentSearch implements Serializable {

    /**
     * 搜索信息
     */
    private String searchInfo;

    /**
     * 用户id
     */
    @JsonIgnore
    private Long unionId;
}

