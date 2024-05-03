package site.cilicili.frontend.carousel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselDto;
import site.cilicili.frontend.carousel.domain.pojo.VideoCarouselEntity;

/**
 * (VideoCarousel)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:45
 */
public interface VideoCarouselService extends IService<VideoCarouselEntity> {

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
     * @param videoCarousel 筛选条件
     * @return 查询结果
     */
    R queryAll(final VideoCarouselDto videoCarousel);

    /**
     * 新增数据
     *
     * @param videoCarousel 实例对象
     * @return 实例对象
     */
    R insert(final VideoCarouselDto videoCarousel);

    /**
     * 修改数据
     *
     * @param videoCarousel 实例对象
     * @return 实例对象
     */
    R update(final VideoCarouselDto videoCarousel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final VideoCarouselDto id);

    R getCarouselList();
}
