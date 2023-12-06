package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysUserRole;
import site.cilicili.common.util.R;

/**
 * (SysUserRole)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    R queryById(final String sysRoleRoleCode);

    /**
     * 全查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysUserRole sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    R insert(final SysUserRole sysUserRole);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    R update(final SysUserRole sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysRoleRoleCode);
}
       

