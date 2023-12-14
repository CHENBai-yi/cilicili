package site.cilicili.backend.menu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.common.util.R;

/**
 * (SysMenu)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:01:59
 */
public interface SysMenuService extends IService<SysMenuEntity> {

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
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysMenuEntity sysMenu);

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    R insert(final SysMenuEntity sysMenu);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    R update(final SysMenuEntity sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

