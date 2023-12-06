package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysRole;
import site.cilicili.common.util.R;

/**
 * (SysRole)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
public interface SysRoleService extends IService<SysRole> {

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
    R queryAll(final SysRole sysRole);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    R insert(final SysRole sysRole);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    R update(final SysRole sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

