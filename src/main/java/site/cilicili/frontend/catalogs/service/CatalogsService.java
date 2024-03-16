package site.cilicili.frontend.catalogs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.catalogs.domain.pojo.CatalogsEntity;

/**
 * (Catalogs)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:01
 */
public interface CatalogsService extends IService<CatalogsEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param catalogId 主键
     * @return 实例对象
     */
    R queryById(final Integer catalogId);

    /**
     * 全查询
     *
     * @param catalogs 筛选条件
     * @return 查询结果
     */
    R queryAll(final CatalogsEntity catalogs);

    /**
     * 新增数据
     *
     * @param catalogs 实例对象
     * @return 实例对象
     */
    R insert(final CatalogsEntity catalogs);

    /**
     * 修改数据
     *
     * @param catalogs 实例对象
     * @return 实例对象
     */
    R update(final CatalogsEntity catalogs);

    /**
     * 通过主键删除数据
     *
     * @param catalogId 主键
     * @return 是否成功
     */
    R deleteById(final Integer catalogId);

}
