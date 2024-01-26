package site.cilicili.backend.notice.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.notice.domain.dto
 * Date:2024/1/25 20:47
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NoticeListQueryParamResponse {
    private Object records;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Records extends SysNoticeDto {
        private SysRoleDto.User createdByUser;
        private SysRoleDto.User updatedByUser;
        private List<SysNoticeToUserEntity> noticeToUser;
    }
}
