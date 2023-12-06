package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysRole;
import site.cilicili.backend.mapper.SysRoleMapper;
import site.cilicili.backend.service.SysRoleService;
import site.cilicili.common.util.R;

/**
 * (SysRole)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@RequiredArgsConstructor
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

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
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysRole sysRole) {
        return R.ok().setData(baseMapper.queryAll(sysRole));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRole sysRole) {
        baseMapper.insert(sysRole);
        return R.ok().setData(sysRole);
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRole sysRole) {
        baseMapper.update(sysRole);
        return R.ok().setData(this.queryById(sysRole.getId()));
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


