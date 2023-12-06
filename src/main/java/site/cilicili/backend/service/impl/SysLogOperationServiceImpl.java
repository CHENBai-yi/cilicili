package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysLogOperation;
import site.cilicili.backend.mapper.SysLogOperationMapper;
import site.cilicili.backend.service.SysLogOperationService;
import site.cilicili.common.util.R;

/**
 * (SysLogOperation)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:42
 */
@RequiredArgsConstructor
@Service("sysLogOperationService")
public class SysLogOperationServiceImpl extends ServiceImpl<SysLogOperationMapper, SysLogOperation> implements SysLogOperationService {

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
     * @param sysLogOperation 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysLogOperation sysLogOperation) {
        return R.ok().setData(baseMapper.queryAll(sysLogOperation));
    }

    /**
     * 新增数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysLogOperation sysLogOperation) {
        baseMapper.insert(sysLogOperation);
        return R.ok().setData(sysLogOperation);
    }

    /**
     * 修改数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysLogOperation sysLogOperation) {
        baseMapper.update(sysLogOperation);
        return R.ok().setData(this.queryById(sysLogOperation.getId()));
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


