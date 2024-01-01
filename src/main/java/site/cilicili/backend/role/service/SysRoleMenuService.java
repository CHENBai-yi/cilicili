package site.cilicili.backend.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.role.domain.dto.RoleButtonMenuRequest;
import site.cilicili.backend.role.domain.pojo.SysRoleMenuEntity;
import site.cilicili.common.util.R;

/**
 * (SysRoleMenu)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:21:21
 */
public interface SysRoleMenuService extends IService<SysRoleMenuEntity> {

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
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysRoleMenuEntity sysRoleMenu);

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    R insert(final SysRoleMenuEntity sysRoleMenu);

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    R update(final SysRoleMenuEntity sysRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysRoleRoleCode);

    R getRoleMenuList(RoleButtonMenuRequest roleCode);

}
       

