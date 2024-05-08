package site.cilicili.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.user.domain.dto.RemoveRoleUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysUserRole)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-31 14:58:48
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    R queryById(final String sysRoleRoleCode);

    SysUserRoleEntity queryByUsername(String sysUserUsername);

    /**
     * 全查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysUserRoleEntity sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    R insert(final SysUserRoleEntity sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    R update(final SysUserRoleEntity sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysRoleRoleCode);

    boolean insertSysUserRoleList(List<SysUserRoleEntity> toList);

    R removeRoleUser(RemoveRoleUserRequest removeRoleUserRequest);

    boolean updateUserRoleName(SysUserRoleEntity sysUserRoleEntity, final String username);
}
