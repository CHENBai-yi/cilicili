package site.cilicili.common.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;

import java.util.List;

/**
 * (DatabaseConnection)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-06 09:24:05
 */
@Mapper
public interface DatabaseConnectionMapper extends BaseMapper<DatabaseConnection> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DatabaseConnection queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param databaseConnection 查询条件
     * @return 对象列表
     */
    List<DatabaseConnection> queryAll(DatabaseConnection databaseConnection);

    /**
     * 统计总行数
     *
     * @param databaseConnection 查询条件
     * @return 总行数
     */
    long count(DatabaseConnection databaseConnection);

    /**
     * 新增数据
     *
     * @param databaseConnection 实例对象
     * @return 影响行数
     */
    int insert(DatabaseConnection databaseConnection);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<DatabaseConnection> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<DatabaseConnection> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<DatabaseConnection> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<DatabaseConnection> entities);

    /**
     * 修改数据
     *
     * @param databaseConnection 实例对象
     * @return 影响行数
     */
    int update(DatabaseConnection databaseConnection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);
}


