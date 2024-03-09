package site.cilicili.frontend.categories.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.categories.domain.dto.AddCategoryRequest;
import site.cilicili.frontend.categories.domain.dto.GetCategoryListRequest;
import site.cilicili.frontend.categories.domain.pojo.CategoriesEntity;
import site.cilicili.frontend.subjects.domain.dto.EditCategoryRequest;

/**
 * (Categories)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:15
 */
public interface CategoriesService extends IService<CategoriesEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Integer id);

    /**
     * 全查询
     *
     * @param categories 筛选条件
     * @return 查询结果
     */
    R queryAll(final CategoriesEntity categories);

    /**
     * 新增数据
     *
     * @param categories 实例对象
     * @return 实例对象
     */
    R insert(final CategoriesEntity categories);

    /**
     * 修改数据
     *
     * @param categories 实例对象
     * @return 实例对象
     */
    R update(final CategoriesEntity categories);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Integer id);

    R getCategoryList(GetCategoryListRequest categories);

    R editCategory(EditCategoryRequest categories);

    R deleteCategoryById(CategoriesEntity id);

    R addCategory(AddCategoryRequest categories);
}
