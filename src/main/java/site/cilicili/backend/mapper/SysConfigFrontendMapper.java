package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysConfigFrontend;

import java.util.List;

/**
 * (SysConfigFrontend)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@Mapper
public interface SysConfigFrontendMapper extends BaseMapper<SysConfigFrontend> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysConfigFrontend queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysConfigFrontend 查询条件
     * @return 对象列表
     */
    List<SysConfigFrontend> queryAll(SysConfigFrontend sysConfigFrontend);

    /**
     * 统计总行数
     *
     * @param sysConfigFrontend 查询条件
     * @return 总行数
     */
    long count(SysConfigFrontend sysConfigFrontend);

    /**
     * 新增数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 影响行数
     */
    int insert(SysConfigFrontend sysConfigFrontend);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigFrontend> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysConfigFrontend> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigFrontend> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysConfigFrontend> entities);

    /**
     * 修改数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 影响行数
     */
    int update(SysConfigFrontend sysConfigFrontend);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}


