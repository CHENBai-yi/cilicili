package site.cilicili.backend.api.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.backend.api.domain.pojo.SysApiEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.io.Serializable;
import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:21:43
 */

/**
 * (SysApi)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:43
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysApi数据传输类")
public class SysApiDto implements Serializable {
    private static final long serialVersionUID = -96896152350797501L;
    private List<Records> records;
    /**
     * 页
     */
    @JsonProperty(value = "page")
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 总数
     */
    private Integer total;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }

    @Getter
    @Setter
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysApiEntity {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
    }
}
