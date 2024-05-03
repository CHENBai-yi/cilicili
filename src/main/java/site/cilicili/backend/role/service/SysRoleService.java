package site.cilicili.backend.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.role.domain.dto.*;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.common.util.R;

/**
 * (SysRole)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:21
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Long id);

    /**
     * 全查询
     *
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysRoleEntity sysRole);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    R insert(final SysRoleEntity sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    R update(final SysRoleEntity sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R queryRoleListByParam(RoleListQueryParam queryParam);

    R editRole(EditRequest sysRole);

    R deleteRoleById(EditRequest sysRole);

    R editRoleDeptDataPermission(EditRoleDeptDataPermissionRequest editRoleDeptDataPermissionRequest);

    R queryUserByRole(QueryUserByRoleRequest queryUserByRoleRequest);

    R editRoleMenu(EditRoleMenuRequest editRoleMenuRequest);

    R editRoleApi(EditRoleApiRequest editRoleApiRequest);

    R addRoleUser(AddRoleUserRequest addRoleUserRequest);
}
