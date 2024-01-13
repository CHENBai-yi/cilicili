package site.cilicili.backend.dept.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.dept.domain.pojo.SysDeptUserEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 16:36:52
 */

/**
 * (SysDeptUser)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:52
 */
@Mapper
public interface SysDeptUserMapper extends BaseMapper<SysDeptUserEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param sysDeptDeptCode 主键
     * @return 实例对象
     */
    SysDeptUserEntity queryById(String sysDeptDeptCode);

    /**
     * 查询指定行数据
     *
     * @param sysDeptUser 查询条件
     * @return 对象列表
     */
    List<SysDeptUserEntity> queryAll(SysDeptUserEntity sysDeptUser);

    /**
     * 统计总行数
     *
     * @param sysDeptUser 查询条件
     * @return 总行数
     */
    long count(SysDeptUserEntity sysDeptUser);

    /**
     * 新增数据
     *
     * @param sysDeptUser 实例对象
     * @return 影响行数
     */
    int insert(SysDeptUserEntity sysDeptUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDeptUserEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDeptUserEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDeptUserEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysDeptUserEntity> entities);

    /**
     * 修改数据
     *
     * @param sysDeptUser 实例对象
     * @return 影响行数
     */
    int update(SysDeptUserEntity sysDeptUser);

    /**
     * 通过主键删除数据
     *
     * @param sysDeptDeptCode 主键
     * @return 影响行数
     */
    int deleteById(String sysDeptDeptCode);

    int insertDeptUserList(List<SysDeptUserEntity> toList);

    int updateDeptUserList(List<SysDeptUserEntity> toList);

    int insertSysDeptUserList(List<SysDeptUserEntity> toList);

    int removeDeptUser(SysDeptUserEntity r);
}
