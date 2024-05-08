package site.cilicili.backend.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.role.domain.pojo.SysRoleButtonEntity;

import java.util.List;

/**
 * PROJECT: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-29 21:20:55
 */

/**
 * (SysRoleButton)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:55
 */
@Mapper
public interface SysRoleButtonMapper extends BaseMapper<SysRoleButtonEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    SysRoleButtonEntity queryById(String sysRoleRoleCode);

    /**
     * 查询指定行数据
     *
     * @param sysRoleButton 查询条件
     * @return 对象列表
     */
    List<SysRoleButtonEntity> queryAll(SysRoleButtonEntity sysRoleButton);

    /**
     * 统计总行数
     *
     * @param sysRoleButton 查询条件
     * @return 总行数
     */
    long count(SysRoleButtonEntity sysRoleButton);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleButtonEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleButtonEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleButtonEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRoleButtonEntity> entities);

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 影响行数
     */
    int update(SysRoleButtonEntity sysRoleButton);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 影响行数
     */
    int deleteById(String sysRoleRoleCode);

    List<String> findButtons(String roleCode);
}
