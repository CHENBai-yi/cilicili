package site.cilicili.frontend.bars.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;

/**
 * (Bars)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:41
 */
public interface BarsService extends IService<BarsEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param barId 主键
     * @return 实例对象
     */
    R queryById(final Integer barId);

    /**
     * 全查询
     *
     * @param bars 筛选条件
     * @return 查询结果
     */
    R queryAll(final BarsEntity bars);

    /**
     * 新增数据
     *
     * @param bars 实例对象
     * @return 实例对象
     */
    R insert(final BarsEntity bars);

    /**
     * 修改数据
     *
     * @param bars 实例对象
     * @return 实例对象
     */
    R update(final BarsEntity bars);

    /**
     * 通过主键删除数据
     *
     * @param barId 主键
     * @return 是否成功
     */
    R deleteById(final Integer barId);

    R updateVideoUrl(BarsEntity bars);
}
