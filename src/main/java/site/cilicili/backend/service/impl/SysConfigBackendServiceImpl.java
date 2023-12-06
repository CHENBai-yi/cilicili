package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysConfigBackend;
import site.cilicili.backend.mapper.SysConfigBackendMapper;
import site.cilicili.backend.service.SysConfigBackendService;
import site.cilicili.common.util.R;

/**
 * (SysConfigBackend)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@RequiredArgsConstructor
@Service("sysConfigBackendService")
public class SysConfigBackendServiceImpl extends ServiceImpl<SysConfigBackendMapper, SysConfigBackend> implements SysConfigBackendService {

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
    public R queryAll(SysConfigBackend sysConfigBackend) {
        return R.ok().setData(baseMapper.queryAll(sysConfigBackend));
    }

    /**
     * 新增数据
     *
     * @param sysConfigBackend 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysConfigBackend sysConfigBackend) {
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
    public R update(SysConfigBackend sysConfigBackend) {
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

}


