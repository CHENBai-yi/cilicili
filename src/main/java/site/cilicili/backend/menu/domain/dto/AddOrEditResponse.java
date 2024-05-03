package site.cilicili.backend.menu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.util.List;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.menu.domain.dto
 * Date:2024/1/15 15:12
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = "handler")
public class AddOrEditResponse extends SysMenuEntity {
    private SysRoleDto role;
    private SysRoleDto.User createdByUser;
    private SysRoleDto.User updatedByUser;
    private List<SysMenuDto.Records> children;
    private List<SysButtonEntity> button;
}
