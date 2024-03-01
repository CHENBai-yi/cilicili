package site.cilicili.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-31 14:58:48
 */

/**
 * (SysUserRole)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-31 14:58:48
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    SysUserRoleEntity queryById(String sysRoleRoleCode);

    SysUserRoleEntity queryByUsername(String sysUserUsername);

    /**
     * 查询指定行数据
     *
     * @param sysUserRole 查询条件
     * @return 对象列表
     */
    List<SysUserRoleEntity> queryAll(SysUserRoleEntity sysUserRole);

    /**
     * 统计总行数
     *
     * @param sysUserRole 查询条件
     * @return 总行数
     */
    long count(SysUserRoleEntity sysUserRole);

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int insert(SysUserRoleEntity sysUserRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRoleEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserRoleEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserRoleEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserRoleEntity> entities);

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 影响行数
     */
    int update(SysUserRoleEntity sysUserRole);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 影响行数
     */
    int deleteById(String sysRoleRoleCode);

    int insertSysUserRoleList(List<SysUserRoleEntity> toList);

    int updateUserRoleName(@Param("entity") SysUserRoleEntity sysUserRoleEntity, @Param("newName") final String username);
}
