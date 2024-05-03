package site.cilicili.frontend.carousel.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.carousel.domain.dto.QueryCarouselResponse;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselDto;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselVo;
import site.cilicili.frontend.carousel.domain.pojo.VideoCarouselEntity;
import site.cilicili.frontend.carousel.mapper.VideoCarouselMapper;
import site.cilicili.frontend.carousel.service.VideoCarouselService;

import java.util.Optional;

/**
 * (VideoCarousel) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:45
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("videoCarouselService")
public class VideoCarouselServiceImpl extends ServiceImpl<VideoCarouselMapper, VideoCarouselEntity>
        implements VideoCarouselService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Long id) {
        return Optional.ofNullable(id)
                .map(idd -> baseMapper.queryById(idd))
                .map(videoCarouselEntity -> BeanUtil.copyProperties(videoCarouselEntity, VideoCarouselDto.class))
                .map(videoCarouselDto -> R.yes("Success.").setRecords(videoCarouselDto))
                .orElse(R.no("Fail."));
    }

    /**
     * 全查询
     *
     * @param videoCarousel 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(VideoCarouselDto videoCarousel) {
        return Optional.ofNullable(baseMapper.count(new VideoCarouselEntity()))
                .filter(f -> f > 0)
                .flatMap(total -> Optional.ofNullable(baseMapper.queryAllByParam(videoCarousel))
                        .map(videoCarouselEntities ->
                                BeanUtil.copyToList(videoCarouselEntities, VideoCarouselDto.class))
                        .map(videoCarouselDtos -> R.yes("Success.")
                                .setData(QueryCarouselResponse.builder()
                                        .records(videoCarouselDtos)
                                        .page(videoCarousel.getPageNum())
                                        .pageSize(videoCarousel.getPageSize())
                                        .total(total.intValue())
                                        .build())))
                .orElse(R.no("Fail"));
    }

    /**
     * 新增数据
     *
     * @param videoCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(VideoCarouselDto videoCarousel) {
        return Optional.of(baseMapper.insert(BeanUtil.copyProperties(videoCarousel, VideoCarouselEntity.class)))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElse(R.no("Fail."));
    }

    /**
     * 修改数据
     *
     * @param videoCarousel 实例对象
     * @return 实例对象
     */
    @Override
    public R update(VideoCarouselDto videoCarousel) {
        return Optional.ofNullable(videoCarousel.getId())
                .map(idd -> baseMapper.updateById(BeanUtil.copyProperties(videoCarousel, VideoCarouselEntity.class)))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElseThrow(() ->
                        AppException.builder().error(Error.COMMON_EXCEPTION).build());
    }

    /**
     * 通过主键删除数据
     *
     * @param videoCarousel 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(VideoCarouselDto videoCarousel) {
        return Optional.ofNullable(videoCarousel.getId())
                .map(idd -> baseMapper.deleteById(idd))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElseThrow(() ->
                        AppException.builder().error(Error.COMMON_EXCEPTION).build());
    }

    @Transactional(readOnly = true)
    @Override
    public R getCarouselList() {
        return Optional.ofNullable(baseMapper.getCarouselListByTime())
                .map(videoCarouselEntities -> BeanUtil.copyToList(videoCarouselEntities, VideoCarouselVo.class))
                .map(videoCarouselVos -> R.yes("Success.").setRecords(videoCarouselVos))
                .orElse(R.no("Fail."));
    }
}
