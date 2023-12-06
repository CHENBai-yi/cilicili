package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysRoleButton;

import java.util.List;

/**
 * (SysRoleButton)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Mapper
public interface SysRoleButtonMapper extends BaseMapper<SysRoleButton> {
    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    SysRoleButton queryById(String sysRoleRoleCode);

    /**
     * 查询指定行数据
     *
     * @param sysRoleButton 查询条件
     * @return 对象列表
     */
    List<SysRoleButton> queryAll(SysRoleButton sysRoleButton);

    /**
     * 统计总行数
     *
     * @param sysRoleButton 查询条件
     * @return 总行数
     */
    long count(SysRoleButton sysRoleButton);

    /**
     * 新增数据
     *
     * @param sysRoleButton 实例对象
     * @return 影响行数
     */
    int insert(SysRoleButton sysRoleButton);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleButton> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleButton> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleButton> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRoleButton> entities);

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 影响行数
     */
    int update(SysRoleButton sysRoleButton);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 影响行数
     */
    int deleteById(String sysRoleRoleCode);
}


