package site.cilicili.backend.notice.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.notice.domain.dto
 * Date:2024/1/26 16:45
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SendNoticeRequest extends SysNoticeDto {
    private List<SysNoticeToUserEntity> noticeToUser;
}
