package site.cilicili.backend.config.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.config.domain.dto.SysConfigBackendDto;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.mapper.SysConfigBackendMapper;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysConfigBackend) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:43
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysConfigBackendService")
public class SysConfigBackendServiceImpl extends ServiceImpl<SysConfigBackendMapper, SysConfigBackendEntity>
        implements SysConfigBackendService {

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
     * @param sysConfigBackend 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysConfigBackendEntity sysConfigBackend) {
        return R.ok().setData(baseMapper.queryAll(sysConfigBackend));
    }

    /**
     * 新增数据
     *
     * @param sysConfigBackend 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysConfigBackendEntity sysConfigBackend) {
        baseMapper.insert(sysConfigBackend);
        return R.ok().setData(sysConfigBackend);
    }

    /**
     * 修改数据
     *
     * @param sysConfigBackend 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysConfigBackendEntity sysConfigBackend) {
        baseMapper.update(sysConfigBackend);
        return R.ok().setData(this.queryById(sysConfigBackend.getId()));
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

    @Override
    public R queryConfigBackendAll() {
        return Optional.ofNullable(baseMapper.queryConfigBackendAll())
                .map(records -> R.yes("Success.")
                        .setData(SysConfigBackendDto.builder().records(records).build()))
                .orElse(R.no("没有更多了."));
    }
}
