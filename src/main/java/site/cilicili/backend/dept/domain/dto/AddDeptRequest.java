package site.cilicili.backend.dept.domain.dto;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.role.domain.dto.SysRoleDto;

import java.util.Map;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.dept.domain.dto
 * Date:2024/1/9 22:08
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddDeptRequest extends SysDeptEntity {
    private SysRoleDto.User leaderUser;

    public void setLeaderUser(final Map<String, Object> leaderUser) {
        this.leaderUser = BeanUtil.copyProperties(leaderUser, SysRoleDto.User.class);
    }
}
