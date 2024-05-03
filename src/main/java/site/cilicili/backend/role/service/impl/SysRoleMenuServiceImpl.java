package site.cilicili.backend.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.role.domain.dto.RoleButtonMenuRequest;
import site.cilicili.backend.role.domain.pojo.SysRoleMenuEntity;
import site.cilicili.backend.role.mapper.SysRoleMenuMapper;
import site.cilicili.backend.role.service.SysRoleMenuService;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.Optional;

/**
 * (SysRoleMenu) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:21:21
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysRoleMenuService")
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenuEntity>
        implements SysRoleMenuService {

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
    public R queryAll(SysRoleMenuEntity sysRoleMenu) {
        return R.ok().setData(baseMapper.queryAll(sysRoleMenu));
    }

    /**
     * 新增数据
     *
     * @param sysRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleMenuEntity sysRoleMenu) {
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
    public R update(SysRoleMenuEntity sysRoleMenu) {
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

    @Override
    @Transactional(readOnly = true)
    public R getRoleMenuList(final RoleButtonMenuRequest roleCode) {
        return Optional.ofNullable(baseMapper.selectList(
                        new QueryWrapper<SysRoleMenuEntity>().eq("sys_role_role_code", roleCode.roleCode())))
                .map(sysRoleMenuEntities -> R.yes("Success").setData("records", sysRoleMenuEntities))
                .orElse(R.no("Fail"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean insertOrBatch(final List<SysRoleMenuEntity> roleMenu) {
        return baseMapper.insertOrUpdateBatch(roleMenu) > 0;
    }
}
