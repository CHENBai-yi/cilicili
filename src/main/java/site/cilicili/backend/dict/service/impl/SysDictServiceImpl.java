package site.cilicili.backend.dict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.dict.domain.dto.SysDictDto;
import site.cilicili.backend.dict.domain.pojo.SysDictEntity;
import site.cilicili.backend.dict.mapper.SysDictMapper;
import site.cilicili.backend.dict.service.SysDictService;
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
        return Optional.ofNullable(baseMapper.queryDictAll()).map(records -> R.yes("Success.").setData(SysDictDto.builder().records(records).build())).orElse(R.no("没有更多了."));
    }

}


