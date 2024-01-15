package site.cilicili.backend.button.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysButton)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:46:33
 */
public interface SysButtonService extends IService<SysButtonEntity> {

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
    R queryAll(final SysButtonEntity sysButton);

    /**
     * 新增数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    R insert(final SysButtonEntity sysButton);

    /**
     * 修改数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    R update(final SysButtonEntity sysButton);

    /**
     * 通过主键删除数据
     *
     * @param menuName 主键
     * @return 是否成功
     */
    R deleteById(final String menuName);

    boolean insertOrUpdate(List<SysButtonEntity> button);
}
