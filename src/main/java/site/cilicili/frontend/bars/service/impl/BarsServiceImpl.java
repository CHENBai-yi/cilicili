package site.cilicili.frontend.bars.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.util.R;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;
import site.cilicili.frontend.bars.mapper.BarsMapper;
import site.cilicili.frontend.bars.service.BarsService;

import java.util.Optional;

/**
 * (Bars) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:41
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("barsService")
public class BarsServiceImpl extends ServiceImpl<BarsMapper, BarsEntity> implements BarsService {

    /**
     * 通过ID查询单条数据
     *
     * @param barId 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer barId) {
        return R.ok().setData(baseMapper.queryById(barId));
    }

    /**
     * 全查询
     *
     * @param bars 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(BarsEntity bars) {
        return R.ok().setData(baseMapper.queryAll(bars));
    }

    /**
     * 新增数据
     *
     * @param bars 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(BarsEntity bars) {
        baseMapper.insert(bars);
        return R.ok().setData(bars);
    }

    /**
     * 修改数据
     *
     * @param bars 实例对象
     * @return 实例对象
     */
    @Override
    public R update(BarsEntity bars) {
        baseMapper.update(bars);
        return R.ok().setData(this.queryById(bars.getBarId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param barId 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer barId) {
        boolean del = baseMapper.deleteById(barId) > 0;
        return R.ok().setData(del);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R updateVideoUrl(final BarsEntity bars) {
        return Optional.ofNullable(bars.getUrl())
                .map(id -> updateById(bars))
                .filter(f -> f)
                .map(r -> R.yes("Success."))
                .orElseThrow(() -> new AppException("url不能为空"));
    }
}
