package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysConfigFrontend;
import site.cilicili.backend.mapper.SysConfigFrontendMapper;
import site.cilicili.backend.service.SysConfigFrontendService;
import site.cilicili.common.util.R;

/**
 * (SysConfigFrontend)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@RequiredArgsConstructor
@Service("sysConfigFrontendService")
public class SysConfigFrontendServiceImpl extends ServiceImpl<SysConfigFrontendMapper, SysConfigFrontend> implements SysConfigFrontendService {

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
    public R queryAll(SysConfigFrontend sysConfigFrontend) {
        return R.ok().setData(baseMapper.queryAll(sysConfigFrontend));
    }

    /**
     * 新增数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysConfigFrontend sysConfigFrontend) {
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
    public R update(SysConfigFrontend sysConfigFrontend) {
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

}


