package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysRoleButton;
import site.cilicili.common.util.R;

/**
 * (SysRoleButton)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
public interface SysRoleButtonService extends IService<SysRoleButton> {

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
     * @param sysRoleButton 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysRoleButton sysRoleButton);

    /**
     * 新增数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    R insert(final SysRoleButton sysRoleButton);

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    R update(final SysRoleButton sysRoleButton);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysRoleRoleCode);
}
       

