package site.cilicili.frontend.advertising.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.util.R;
import site.cilicili.frontend.advertising.domain.pojo.VideoAdvertisingEntity;
import site.cilicili.frontend.advertising.mapper.VideoAdvertisingMapper;
import site.cilicili.frontend.advertising.service.VideoAdvertisingService;

/**
 * (VideoAdvertising) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:16
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("videoAdvertisingService")
public class VideoAdvertisingServiceImpl extends ServiceImpl<VideoAdvertisingMapper, VideoAdvertisingEntity> implements VideoAdvertisingService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Long id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param videoAdvertising 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(VideoAdvertisingEntity videoAdvertising) {
        return R.ok().setData(baseMapper.queryAll(videoAdvertising));
    }

    /**
     * 新增数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(VideoAdvertisingEntity videoAdvertising) {
        baseMapper.insert(videoAdvertising);
        return R.ok().setData(videoAdvertising);
    }

    /**
     * 修改数据
     *
     * @param videoAdvertising 实例对象
     * @return 实例对象
     */
    @Override
    public R update(VideoAdvertisingEntity videoAdvertising) {
        baseMapper.update(videoAdvertising);
        return R.ok().setData(this.queryById(videoAdvertising.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Long id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

}


