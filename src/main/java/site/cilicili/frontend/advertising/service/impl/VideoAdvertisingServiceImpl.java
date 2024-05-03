package site.cilicili.frontend.advertising.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.advertising.domain.dto.VideoAdvertisingDto;
import site.cilicili.frontend.advertising.domain.dto.VideoAdvertisingVo;
import site.cilicili.frontend.advertising.domain.pojo.VideoAdvertisingEntity;
import site.cilicili.frontend.advertising.mapper.VideoAdvertisingMapper;
import site.cilicili.frontend.advertising.service.VideoAdvertisingService;
import site.cilicili.frontend.carousel.domain.dto.QueryCarouselResponse;

import java.util.Optional;

/**
 * (VideoAdvertising) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("videoAdvertisingService")
public class VideoAdvertisingServiceImpl extends ServiceImpl<VideoAdvertisingMapper, VideoAdvertisingEntity>
        implements VideoAdvertisingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(VideoAdvertisingDto id) {
        return Optional.ofNullable(id.getId())
                .map(idd -> baseMapper.queryById(idd))
                .map(videoAdvertising -> BeanUtil.copyProperties(videoAdvertising, VideoAdvertisingDto.class))
                .map(videoAdvertisingDto -> R.yes("Success.").setRecords(videoAdvertisingDto))
                .orElse(R.no("Fail."));
    }

    /**
     * 全查询
     *
     * @param videoAdvertising 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(VideoAdvertisingDto videoAdvertising) {
        return Optional.of(baseMapper.count(new VideoAdvertisingEntity()))
                .filter(f -> f > 0)
                .flatMap(total -> Optional.ofNullable(baseMapper.queryAllByParam(videoAdvertising))
                        .map(videoCarouselEntities ->
                                BeanUtil.copyToList(videoCarouselEntities, VideoAdvertisingDto.class))
                        .map(videoCarouselDtos -> R.yes("Success.")
                                .setData(QueryCarouselResponse.builder()
                                        .records(videoCarouselDtos)
                                        .page(videoAdvertising.getPageNum())
                                        .pageSize(videoAdvertising.getPageSize())
                                        .total(total.intValue())
                                        .build())))
                .orElse(R.no("Fail"));
    }

    /**
     * 新增数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(VideoAdvertisingDto videoAdvertising) {
        return Optional.of(baseMapper.insert(BeanUtil.copyProperties(videoAdvertising, VideoAdvertisingEntity.class)))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElse(R.no("Fail."));
    }

    /**
     * 修改数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    @Override
    public R update(VideoAdvertisingDto videoAdvertising) {
        return Optional.ofNullable(videoAdvertising.getId())
                .map(idd ->
                        baseMapper.updateById(BeanUtil.copyProperties(videoAdvertising, VideoAdvertisingEntity.class)))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElseThrow(() ->
                        AppException.builder().error(Error.COMMON_EXCEPTION).build());
    }

    /**
     * 通过主键删除数据
     *
     * @param videoAdvertising 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(VideoAdvertisingDto videoAdvertising) {
        return Optional.ofNullable(videoAdvertising.getId())
                .map(idd -> baseMapper.deleteById(idd))
                .filter(f -> f > 0)
                .map(r -> R.yes("Success."))
                .orElseThrow(() ->
                        AppException.builder().error(Error.COMMON_EXCEPTION).build());
    }

    @Transactional(readOnly = true)
    @Override
    public R getAdverList() {
        return Optional.ofNullable(baseMapper.getAdverListByTime())
                .map(videoAdvertisingEntities ->
                        BeanUtil.copyToList(videoAdvertisingEntities, VideoAdvertisingVo.class))
                .map(videoCarouselVos -> R.yes("Success.").setRecords(videoCarouselVos))
                .orElse(R.no("Fail."));
    }
}
