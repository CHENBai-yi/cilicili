package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysButton;
import site.cilicili.common.util.R;

/**
 * (SysButton)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
public interface SysButtonService extends IService<SysButton> {

    /**
     * 通过ID查询单条数据
     *
     * @param menuName 主键
     * @return 实例对象
     */
    R queryById(final String menuName);

    /**
     * 全查询
     *
     * @param sysButton 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysButton sysButton);

    /**
     * 新增数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    R insert(final SysButton sysButton);

    /**
     * 修改数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    R update(final SysButton sysButton);

    /**
     * 通过主键删除数据
     *
     * @param menuName 主键
     * @return 是否成功
     */
    R deleteById(final String menuName);
}
       

