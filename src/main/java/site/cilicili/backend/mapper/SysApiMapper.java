package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysApi;

import java.util.List;

/**
 * (SysApi)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@Mapper
public interface SysApiMapper extends BaseMapper<SysApi> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysApi queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysApi 查询条件
     * @return 对象列表
     */
    List<SysApi> queryAll(SysApi sysApi);

    /**
     * 统计总行数
     *
     * @param sysApi 查询条件
     * @return 总行数
     */
    long count(SysApi sysApi);

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 影响行数
     */
    int insert(SysApi sysApi);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysApi> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysApi> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysApi> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysApi> entities);

    /**
     * 修改数据
     *
     * @param sysApi 实例对象
     * @return 影响行数
     */
    int update(SysApi sysApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}


