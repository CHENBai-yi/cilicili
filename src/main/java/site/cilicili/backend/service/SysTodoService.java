package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysTodo;
import site.cilicili.common.util.R;

/**
 * (SysTodo)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
public interface SysTodoService extends IService<SysTodo> {

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
     * @param sysTodo 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysTodo sysTodo);

    /**
     * 新增数据
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    R insert(final SysTodo sysTodo);

    /**
     * 修改数据
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    R update(final SysTodo sysTodo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

