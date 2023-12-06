package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysUserRole;
import site.cilicili.backend.mapper.SysUserRoleMapper;
import site.cilicili.backend.service.SysUserRoleService;
import site.cilicili.common.util.R;

/**
 * (SysUserRole)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
@RequiredArgsConstructor
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    @Override
    public R queryById(String sysRoleRoleCode) {
        return R.ok().setData(baseMapper.queryById(sysRoleRoleCode));
    }

    /**
     * 全查询
     *
     * @param sysUserRole 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysUserRole sysUserRole) {
        return R.ok().setData(baseMapper.queryAll(sysUserRole));
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUserRole sysUserRole) {
        baseMapper.insert(sysUserRole);
        return R.ok().setData(sysUserRole);
    }

    /**
     * 修改数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysUserRole sysUserRole) {
        baseMapper.update(sysUserRole);
        return R.ok().setData(this.queryById(sysUserRole.getSysRoleRoleCode()));
    }

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(String sysRoleRoleCode) {
        boolean del = baseMapper.deleteById(sysRoleRoleCode) > 0;
        return R.ok().setData(del);
    }

}


