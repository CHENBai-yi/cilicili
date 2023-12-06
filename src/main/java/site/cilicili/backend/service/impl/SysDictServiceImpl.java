package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysDict;
import site.cilicili.backend.mapper.SysDictMapper;
import site.cilicili.backend.service.SysDictService;
import site.cilicili.common.util.R;

/**
 * (SysDict)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
@RequiredArgsConstructor
@Service("sysDictService")
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

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
    public R queryAll(SysDict sysDict) {
        return R.ok().setData(baseMapper.queryAll(sysDict));
    }

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDict sysDict) {
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
    public R update(SysDict sysDict) {
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

}


