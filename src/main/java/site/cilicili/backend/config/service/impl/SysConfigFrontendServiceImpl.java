package site.cilicili.backend.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedFrontendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.dto.SysConfigFrontendDto;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;
import site.cilicili.backend.config.mapper.SysConfigFrontendMapper;
import site.cilicili.backend.config.service.SysConfigFrontendService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysConfigFrontend) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
@RequiredArgsConstructor
@Service("sysConfigFrontendService")
@CacheConfig(cacheNames = {"SysConfigFrontend"})
public class SysConfigFrontendServiceImpl extends ServiceImpl<SysConfigFrontendMapper, SysConfigFrontendEntity> implements SysConfigFrontendService {

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
     * @param sysConfigFrontend 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysConfigFrontendEntity sysConfigFrontend) {
        return R.ok().setData(baseMapper.queryAll(sysConfigFrontend));
    }

    /**
     * 新增数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysConfigFrontendEntity sysConfigFrontend) {
        baseMapper.insert(sysConfigFrontend);
        return R.ok().setData(sysConfigFrontend);
    }

    /**
     * 修改数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysConfigFrontendEntity sysConfigFrontend) {
        baseMapper.update(sysConfigFrontend);
        return R.ok().setData(this.queryById(sysConfigFrontend.getId()));
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

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "SysConfigFrontend", key = "#root.methodName")
    @Override
    public R queryConfigFrontAll() {
        return Optional.ofNullable(baseMapper.queryConfigFrontAll()).map(records -> R.yes("Success.").setData(SysConfigFrontendDto.builder().records(records).build())).orElse(R.no("没有更多了."));
    }

    @Transactional(readOnly = true)
    @Override
    public R getConfigFrontendList(final QueryConfigRequest queryFrontendRequest) {
        return Optional.ofNullable(baseMapper.queryConfigFrontendList(queryFrontendRequest)).map(records -> R.yes("Success.").setData(SysConfigFrontendDto.builder().records(records).build())).orElse(R.no("没有更多了."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R configFrontendAdd(final AddConfigRequest addConfigRequest) {
        return Optional.of(save(BeanUtil.toBean(addConfigRequest, SysConfigFrontendEntity.class))).filter(f -> f).map(f -> R.yes("添加成功.")).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R editConfigFrontend(final AuthUserDetails authUserDetails, final EditedFrontendConfigRequest editedFrontendConfigRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> (baseMapper.selectById(editedFrontendConfigRequest.getId())))
                .filter(sysConfigFrontendEntity -> updateById(editedFrontendConfigRequest))
                .map(sysConfigFrontendEntity -> R.yes(String.format("%1$s修改成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
