package site.cilicili.backend.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedBackendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.dto.SysConfigBackendDto;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.mapper.SysConfigBackendMapper;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
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
@CacheConfig(cacheNames = {"SysConfigBackend"})
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

    @Cacheable(key = "#root.methodName")
    @Transactional(readOnly = true)
    @Override
    public R queryConfigBackendAll() {
        return Optional.ofNullable(baseMapper.queryConfigBackendAll())
                .map(records -> R.yes("Success.")
                        .setData(SysConfigBackendDto.builder()
                                .records(records).build()))
                .orElse(R.no("没有更多了."));
    }

    @Override
    @Transactional(readOnly = true)
    public R getConfigBackendList(final QueryConfigRequest queryBackRequest) {
        return Optional.ofNullable(baseMapper.getConfigBackendList(queryBackRequest))
                .map(records -> R.yes("Success.")
                        .setData(SysConfigBackendDto.builder()
                                .records(records)
                                .page(queryBackRequest.page())
                                .pageSize(queryBackRequest.pageSize())
                                .total(records.size())
                                .build()))
                .orElse(R.no("没有更多了."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(allEntries = true)
    public R configBackendAdd(final AddConfigRequest addConfigRequest) {
        return Optional.of(save(BeanUtil.toBean(addConfigRequest, SysConfigBackendEntity.class)))
                .filter(f -> f)
                .map(f -> R.yes("添加成功."))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(allEntries = true)
    public R editConfigBackend(
            final AuthUserDetails authUserDetails, final EditedBackendConfigRequest editedBackendConfigRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> (baseMapper.selectById(editedBackendConfigRequest.getId())))
                .filter(sysConfigFrontendEntity -> updateById(editedBackendConfigRequest))
                .map(sysConfigFrontendEntity -> R.yes(String.format("%1$s修改成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
