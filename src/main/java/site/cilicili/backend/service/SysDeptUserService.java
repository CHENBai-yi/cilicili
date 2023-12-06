package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysDeptUser;
import site.cilicili.common.util.R;

/**
 * (SysDeptUser)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
public interface SysDeptUserService extends IService<SysDeptUser> {

    /**
     * 通过ID查询单条数据
     *
     * @param sysDeptDeptCode 主键
     * @return 实例对象
     */
    R queryById(final String sysDeptDeptCode);

    /**
     * 全查询
     *
     * @param sysDeptUser 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysDeptUser sysDeptUser);

    /**
     * 新增数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    R insert(final SysDeptUser sysDeptUser);

    /**
     * 修改数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    R update(final SysDeptUser sysDeptUser);

    /**
     * 通过主键删除数据
     *
     * @param sysDeptDeptCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysDeptDeptCode);
}
       

