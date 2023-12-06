package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysApi;
import site.cilicili.common.util.R;

/**
 * (SysApi)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
public interface SysApiService extends IService<SysApi> {

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
     * @param sysApi 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysApi sysApi);

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    R insert(final SysApi sysApi);

    /**
     * 修改数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    R update(final SysApi sysApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

