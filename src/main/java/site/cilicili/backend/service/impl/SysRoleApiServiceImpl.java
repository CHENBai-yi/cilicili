package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysRoleApi;
import site.cilicili.backend.mapper.SysRoleApiMapper;
import site.cilicili.backend.service.SysRoleApiService;
import site.cilicili.common.util.R;

/**
 * (SysRoleApi)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@RequiredArgsConstructor
@Service("sysRoleApiService")
public class SysRoleApiServiceImpl extends ServiceImpl<SysRoleApiMapper, SysRoleApi> implements SysRoleApiService {

    /**
     * 全查询
     *
     * @param sysRoleApi 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysRoleApi sysRoleApi) {
        return R.ok().setData(baseMapper.queryAll(sysRoleApi));
    }

    /**
     * 新增数据
     *
     * @param sysRoleApi 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleApi sysRoleApi) {
        baseMapper.insert(sysRoleApi);
        return R.ok().setData(sysRoleApi);
    }

    /**
     * 修改数据
     *
     * @param sysRoleApi 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleApi sysRoleApi) {
        baseMapper.update(sysRoleApi);
        return R.ok().setData(this.queryAll(sysRoleApi));
    }

}


