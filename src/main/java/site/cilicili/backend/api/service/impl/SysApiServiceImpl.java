package site.cilicili.backend.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.api.domain.dto.AddApiRequest;
import site.cilicili.backend.api.domain.dto.DeleteOrQueryApiRequest;
import site.cilicili.backend.api.domain.dto.GetApiListRequest;
import site.cilicili.backend.api.domain.dto.SysApiDto;
import site.cilicili.backend.api.domain.pojo.SysApiEntity;
import site.cilicili.backend.api.mapper.SysApiMapper;
import site.cilicili.backend.api.service.SysApiService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysApi) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:43
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysApiService")
public class SysApiServiceImpl extends ServiceImpl<SysApiMapper, SysApiEntity> implements SysApiService {

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
     * @param sysApi 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysApiEntity sysApi) {
        return R.ok().setData(baseMapper.queryAll(sysApi));
    }

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysApiEntity sysApi) {
        baseMapper.insert(sysApi);
        return R.ok().setData(sysApi);
    }

    /**
     * 修改数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysApiEntity sysApi) {
        baseMapper.update(sysApi);
        return R.ok().setData(this.queryById(sysApi.getId()));
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
    @Transactional(readOnly = true)
    public R getApiList(final GetApiListRequest apiListRequest) {
        return Optional.ofNullable(baseMapper.getApiList(apiListRequest)).map(records -> R.yes("Success").setData(SysApiDto.builder().build().setRecords(records).setTotal(records.size()).setPageNum(apiListRequest.page()).setPageSize(apiListRequest.pageSize()))).orElse(R.no("Fail"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R addApi(final AddApiRequest addApiRequest) {
        return Optional.of(save(BeanUtil.toBean(addApiRequest, SysApiEntity.class))).filter(r -> r).map(r -> R.yes("添加成功.")).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteApiById(final AuthUserDetails authUserDetails, final DeleteOrQueryApiRequest deleteOrQueryApiRequest) {
        return Optional.ofNullable(authUserDetails).map(auth -> baseMapper.selectById(deleteOrQueryApiRequest.id())).filter(this::removeById).map(r -> R.yes(String.format("%1$s删除成功.", authUserDetails.getusername()))).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));

    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R editApi(final SysApiDto.Records editRequest, final AuthUserDetails authUserDetails) {
        return Optional.ofNullable(authUserDetails).map(r -> baseMapper.selectById(editRequest.getId())).filter(sysApiEntity -> updateById(editRequest)).map(r -> R.yes(String.format("%1$s编辑成功.", authUserDetails.getusername()))).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(readOnly = true)
    public R queryApiById(final AuthUserDetails authUserDetails, final DeleteOrQueryApiRequest deleteOrQueryApiRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> baseMapper.selectById(deleteOrQueryApiRequest.id()))
                .map(sysApiEntity -> R.yes("查询成功.").setRecords(sysApiEntity))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
