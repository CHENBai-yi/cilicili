package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysDept;
import site.cilicili.common.util.R;

/**
 * (SysDept)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:39
 */
public interface SysDeptService extends IService<SysDept> {

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
     * @param sysDept 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysDept sysDept);

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    R insert(final SysDept sysDept);

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    R update(final SysDept sysDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
       

