package site.cilicili.backend.dept.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.dept.domain.pojo.SysDeptUserEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysDeptUser)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:52
 */
public interface SysDeptUserService extends IService<SysDeptUserEntity> {

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
    R queryAll(final SysDeptUserEntity sysDeptUser);

    /**
     * 新增数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    R insert(final SysDeptUserEntity sysDeptUser);

    /**
     * 修改数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    R update(final SysDeptUserEntity sysDeptUser);

    /**
     * 通过主键删除数据
     *
     * @param sysDeptDeptCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysDeptDeptCode);

    boolean insertDeptUserList(@Param("toList") List<SysDeptUserEntity> toList);

    boolean updateDeptUserList(@Param("toList") List<SysDeptUserEntity> toList);

    boolean insertSysDeptUserList(@Param("toList") List<SysDeptUserEntity> toList);

    boolean removeByDeptCode(String deptCode);

    boolean removeDeptUser(SysDeptUserEntity r);
}
