package site.cilicili.frontend.catalogs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:00
 */

/**
 * (Catalogs)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:00
 */
@Mapper
public interface CatalogsMapper extends BaseMapper<CatalogsEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param catalogId 主键
     * @return 实例对象
     */
    CatalogsEntity queryById(Integer catalogId);

    /**
     * 查询指定行数据
     *
     * @param catalogs 查询条件
     * @return 对象列表
     */
    List<CatalogsEntity> queryAll(CatalogsEntity catalogs);

    /**
     * 统计总行数
     *
     * @param catalogs 查询条件
     * @return 总行数
     */
    long count(CatalogsEntity catalogs);

    /**
     * 新增数据
     *
     * @param catalogs 实例对象
     * @return 影响行数
     */
    int insert(CatalogsEntity catalogs);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CatalogsEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CatalogsEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CatalogsEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CatalogsEntity> entities);

    /**
     * 修改数据
     *
     * @param catalogs 实例对象
     * @return 影响行数
     */
    int update(CatalogsEntity catalogs);

    /**
     * 通过主键删除数据
     *
     * @param catalogId 主键
     * @return 影响行数
     */
    int deleteById(Integer catalogId);
}


