package site.cilicili.frontend.categories.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.categories.domain.dto.GetCategoryListRequest;
import site.cilicili.frontend.categories.domain.pojo.CategoriesEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:15
 */

/**
 * (Categories)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:15
 */
@Mapper
public interface CategoriesMapper extends BaseMapper<CategoriesEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CategoriesEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param categories 查询条件
     * @return 对象列表
     */
    List<CategoriesEntity> queryAll(CategoriesEntity categories);

    /**
     * 统计总行数
     *
     * @param categories 查询条件
     * @return 总行数
     */
    long count(CategoriesEntity categories);

    /**
     * 新增数据
     *
     * @param categories 实例对象
     * @return 影响行数
     */
    int insert(CategoriesEntity categories);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CategoriesEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CategoriesEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CategoriesEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CategoriesEntity> entities);

    /**
     * 修改数据
     *
     * @param categories 实例对象
     * @return 影响行数
     */
    int update(CategoriesEntity categories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<CategoriesEntity> getCategoryList(GetCategoryListRequest categories);
}
