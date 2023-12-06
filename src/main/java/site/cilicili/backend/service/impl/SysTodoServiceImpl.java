package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysTodo;
import site.cilicili.backend.mapper.SysTodoMapper;
import site.cilicili.backend.service.SysTodoService;
import site.cilicili.common.util.R;

/**
 * (SysTodo)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
@RequiredArgsConstructor
@Service("sysTodoService")
public class SysTodoServiceImpl extends ServiceImpl<SysTodoMapper, SysTodo> implements SysTodoService {

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
     * @param sysTodo 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysTodo sysTodo) {
        return R.ok().setData(baseMapper.queryAll(sysTodo));
    }

    /**
     * 新增数据
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysTodo sysTodo) {
        baseMapper.insert(sysTodo);
        return R.ok().setData(sysTodo);
    }

    /**
     * 修改数据
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysTodo sysTodo) {
        baseMapper.update(sysTodo);
        return R.ok().setData(this.queryById(sysTodo.getId()));
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


