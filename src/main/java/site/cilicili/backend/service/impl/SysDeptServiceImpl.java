package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysDept;
import site.cilicili.backend.mapper.SysDeptMapper;
import site.cilicili.backend.service.SysDeptService;
import site.cilicili.common.util.R;

/**
 * (SysDept)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:39
 */
@RequiredArgsConstructor
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

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
     * @param sysDept 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysDept sysDept) {
        return R.ok().setData(baseMapper.queryAll(sysDept));
    }

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDept sysDept) {
        baseMapper.insert(sysDept);
        return R.ok().setData(sysDept);
    }

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysDept sysDept) {
        baseMapper.update(sysDept);
        return R.ok().setData(this.queryById(sysDept.getId()));
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


