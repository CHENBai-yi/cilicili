package site.cilicili.backend.dict.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.dict.domain.dto.AddDictRequest;
import site.cilicili.backend.dict.domain.dto.QueryAndDeleteRequest;
import site.cilicili.backend.dict.domain.dto.SysDictDto;
import site.cilicili.backend.dict.domain.pojo.SysDictEntity;
import site.cilicili.backend.dict.mapper.SysDictMapper;
import site.cilicili.backend.dict.service.SysDictService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysDict) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-16 12:44:35
 */
@RequiredArgsConstructor
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDictEntity> implements SysDictService {

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
     * @param sysDict 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysDictEntity sysDict) {
        return R.ok().setData(baseMapper.queryAll(sysDict));
    }

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDictEntity sysDict) {
        baseMapper.insert(sysDict);
        return R.ok().setData(sysDict);
    }

    /**
     * 修改数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysDictEntity sysDict) {
        baseMapper.update(sysDict);
        return R.ok().setData(this.queryById(sysDict.getId()));
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
    public R queryDictAll() {
        return Optional.ofNullable(baseMapper.queryDictAll())
                .map(records -> R.yes("Success.")
                        .setData(SysDictDto.builder().records(records).build()))
                .orElse(R.no("没有更多了."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R addDict(final AddDictRequest addDictRequest) {
        return Optional.of(save(BeanUtil.toBean(addDictRequest, SysDictEntity.class))).filter(f -> f)
                .map(r -> R.yes("添加成功."))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(readOnly = true)
    public R queryDictById(final QueryAndDeleteRequest queryAndDeleteRequest) {
        return Optional.ofNullable(baseMapper.queryDictById(queryAndDeleteRequest.id()))
                .map(records -> R.yes("查找成功.").setData(SysDictDto.builder().records(records).build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R editDict(final AuthUserDetails authUserDetails, final SysDictDto.AddOrEditResponse editRequest) {
        return Optional.ofNullable(baseMapper.selectById(editRequest.getId()))
                .filter(records -> !"yesNo_yes".equals(records.getStable()))
                .filter(sysDictEntity -> saveOrUpdate(editRequest))
                .map(sysDictEntity -> R.yes(String.format("%1$s编辑成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteDictById(final AuthUserDetails authUserDetails, final QueryAndDeleteRequest queryAndDeleteRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> baseMapper.selectById(queryAndDeleteRequest.id()))
                .filter(this::removeById)
                .map(sysDictEntity -> R.yes(String.format("%1$s删除成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));

    }

}
