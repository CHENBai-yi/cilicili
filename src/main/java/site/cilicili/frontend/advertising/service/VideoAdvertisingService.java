package site.cilicili.frontend.advertising.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.advertising.domain.pojo.VideoAdvertisingEntity;

/**
 * (VideoAdvertising)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
public interface VideoAdvertisingService extends IService<VideoAdvertisingEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Long id);

    /**
     * 全查询
     *
     * @param videoAdvertising 筛选条件
     * @return 查询结果
     */
    R queryAll(final VideoAdvertisingEntity videoAdvertising);

    /**
     * 新增数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    R insert(final VideoAdvertisingEntity videoAdvertising);

    /**
     * 修改数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    R update(final VideoAdvertisingEntity videoAdvertising);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);
}
