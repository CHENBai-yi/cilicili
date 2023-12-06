package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysGenPluginList;
import site.cilicili.common.util.R;

/**
 * (SysGenPluginList)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:41
 */
public interface SysGenPluginListService extends IService<SysGenPluginList> {

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
     * @param sysGenPluginList 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysGenPluginList sysGenPluginList);

    /**
     * 新增数据
     *
     * @param sysGenPluginList 实例对象
     * @return 实例对象
     */
    R insert(final SysGenPluginList sysGenPluginList);

    /**
     * 修改数据
     *
     * @param sysGenPluginList 实例对象
     * @return 实例对象
     */
    R update(final SysGenPluginList sysGenPluginList);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

