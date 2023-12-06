package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysApi;
import site.cilicili.backend.mapper.SysApiMapper;
import site.cilicili.backend.service.SysApiService;
import site.cilicili.common.util.R;

/**
 * (SysApi)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:37
 */
@RequiredArgsConstructor
@Service("sysApiService")
public class SysApiServiceImpl extends ServiceImpl<SysApiMapper, SysApi> implements SysApiService {

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
    public R queryAll(SysApi sysApi) {
        return R.ok().setData(baseMapper.selectList(new QueryWrapper<SysApi>()));
    }

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysApi sysApi) {
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
    public R update(SysApi sysApi) {
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

}


