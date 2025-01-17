package site.cilicili.backend.menu.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.menu.domain.dto
 * Date:2024/1/4 21:02
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Data
public class GetUserMenuResponse implements Serializable {
    private List<SysMenuDto.Records> records;
    private List<String> buttons;
    private List<String> defaultPageList;
}
