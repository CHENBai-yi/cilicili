package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysUserOnline;

import java.util.List;

/**
 * (SysUserOnline)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@Mapper
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnline> {
    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    SysUserOnline queryById();

    /**
     * 查询指定行数据
     *
     * @param sysUserOnline 查询条件
     * @return 对象列表
     */
    List<SysUserOnline> queryAll(SysUserOnline sysUserOnline);

    /**
     * 统计总行数
     *
     * @param sysUserOnline 查询条件
     * @return 总行数
     */
    long count(SysUserOnline sysUserOnline);

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 影响行数
     */
    int insert(SysUserOnline sysUserOnline);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserOnline> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserOnline> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserOnline> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserOnline> entities);

    /**
     * 修改数据
     *
     * @param sysUserOnline 实例对象
     * @return 影响行数
     */
    int update(SysUserOnline sysUserOnline);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 影响行数
     */
    int deleteById();
}


