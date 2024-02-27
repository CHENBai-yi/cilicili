package site.cilicili.frontend.categories.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.categories.domain.dto.AddCategoryRequest;
import site.cilicili.frontend.categories.domain.dto.GetCategoryListRequest;
import site.cilicili.frontend.categories.domain.dto.GetCategoryListResponse;
import site.cilicili.frontend.categories.domain.pojo.CategoriesEntity;
import site.cilicili.frontend.categories.mapper.CategoriesMapper;
import site.cilicili.frontend.categories.service.CategoriesService;
import site.cilicili.frontend.subjects.domain.dto.EditCategoryRequest;
import site.cilicili.frontend.subjects.domain.pojo.SubjectsEntity;
import site.cilicili.frontend.subjects.service.SubjectsService;

import java.util.Optional;

/**
 * (Categories) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:16
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("categoriesService")
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, CategoriesEntity> implements CategoriesService {

    private final SubjectsService subjectsService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param categories 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(CategoriesEntity categories) {
        return R.ok().setData(baseMapper.queryAll(categories));
    }

    /**
     * 新增数据
     *
     * @param categories 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(CategoriesEntity categories) {
        baseMapper.insert(categories);
        return R.ok().setData(categories);
    }

    /**
     * 修改数据
     *
     * @param categories 实例对象
     * @return 实例对象
     */
    @Override
    public R update(CategoriesEntity categories) {
        baseMapper.update(categories);
        return R.ok().setData(this.queryById(categories.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Transactional(readOnly = true)
    @Override
    public R getCategoryList(final GetCategoryListRequest categories) {
        return Optional.ofNullable(baseMapper.getCategoryList(categories))
                .map(records -> R.yes("Success.").setData(GetCategoryListResponse.builder().records(records).page(categories.getPage()).pageSize(categories.getPageSize()).total(records.size()).build()))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R editCategory(final EditCategoryRequest categories) {
        return Optional.of(saveOrUpdate(BeanUtil.toBean(categories, CategoriesEntity.class)))
                .filter(f -> f)
                .map(f -> R.yes("Success."))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R deleteCategoryById(final CategoriesEntity id) {
        return Optional.of(removeById(id))
                .filter(f -> f)
                .map(f -> R.yes("Success."))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R addCategory(final AddCategoryRequest categories) {
        return Optional.ofNullable(subjectsService.getOne(new QueryWrapper<SubjectsEntity>()
                        .eq("subject_code", categories.getSubjectCode())))
                .map(subjectsEntity -> {
                    final CategoriesEntity categoriesEntity = BeanUtil.toBean(categories, CategoriesEntity.class);
                    categoriesEntity.setSubjectId(subjectsEntity.getId());
                    return saveOrUpdate(categoriesEntity);
                }).filter(f -> f)
                .map(f -> R.yes("Success."))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

}


