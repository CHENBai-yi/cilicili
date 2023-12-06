package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysRoleMenu;
import site.cilicili.backend.mapper.SysRoleMenuMapper;
import site.cilicili.backend.service.SysRoleMenuService;
import site.cilicili.common.util.R;

/**
 * (SysRoleMenu)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:46
 */
@RequiredArgsConstructor
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

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
     * @param sysRoleMenu 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysRoleMenu sysRoleMenu) {
        return R.ok().setData(baseMapper.queryAll(sysRoleMenu));
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleMenu sysRoleMenu) {
        baseMapper.insert(sysRoleMenu);
        return R.ok().setData(sysRoleMenu);
    }

    /**
     * 修改数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleMenu sysRoleMenu) {
        baseMapper.update(sysRoleMenu);
        return R.ok().setData(this.queryById(sysRoleMenu.getSysRoleRoleCode()));
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


