package site.cilicili.backend.role.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.role.domain.dto.RoleButtonMenuRequest;
import site.cilicili.backend.role.service.SysRoleButtonService;
import site.cilicili.common.util.R;

/**
 * (SysRoleButton) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:56
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("role")
@Tag(name = "(SysRoleButton) 表控制层")
public class SysRoleButtonController {
    /**
     * 服务对象
     */
    private final SysRoleButtonService sysRoleButtonService;

    /**
     * 获取角色对应的按钮权限列表
     *
     * @param roleCode 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "获取角色对应的按钮权限列表",
            parameters = {@Parameter(description = "Long 筛选条件")})
    @PostMapping("get-role-button-list")
    public R getRoleButtonList(@RequestBody @NotNull final RoleButtonMenuRequest roleCode) {
        return sysRoleButtonService.getRoleButtonList(roleCode);
    }
}
