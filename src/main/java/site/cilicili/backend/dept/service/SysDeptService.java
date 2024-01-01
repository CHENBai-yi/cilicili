package site.cilicili.backend.dept.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.dept.domain.dto.GetDeptListRequest;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.common.util.R;

/**
 * (SysDept)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:33
 */
public interface SysDeptService extends IService<SysDeptEntity> {

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
    R queryAll(final SysDeptEntity sysDept);

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    R insert(final SysDeptEntity sysDept);

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    R update(final SysDeptEntity sysDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getDeptList(GetDeptListRequest deptListRequest);
}
       

