package site.cilicili.frontend.catalogs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.util.R;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;
import site.cilicili.frontend.catalogs.mapper.CatalogsMapper;
import site.cilicili.frontend.catalogs.service.CatalogsService;

/**
 * (Catalogs) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:01
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("catalogsService")
public class CatalogsServiceImpl extends ServiceImpl<CatalogsMapper, CatalogsEntity> implements CatalogsService {

    /**
     * 通过ID查询单条数据
     *
     * @param catalogId 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer catalogId) {
        return R.ok().setData(baseMapper.queryById(catalogId));
    }

    /**
     * 全查询
     *
     * @param catalogs 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(CatalogsEntity catalogs) {
        return R.ok().setData(baseMapper.queryAll(catalogs));
    }

    /**
     * 新增数据
     *
     * @param catalogs 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(CatalogsEntity catalogs) {
        baseMapper.insert(catalogs);
        return R.ok().setData(catalogs);
    }

    /**
     * 修改数据
     *
     * @param catalogs 实例对象
     * @return 实例对象
     */
    @Override
    public R update(CatalogsEntity catalogs) {
        baseMapper.update(catalogs);
        return R.ok().setData(this.queryById(catalogs.getCatalogId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param catalogId 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer catalogId) {
        boolean del = baseMapper.deleteById(catalogId) > 0;
        return R.ok().setData(del);
    }
}
