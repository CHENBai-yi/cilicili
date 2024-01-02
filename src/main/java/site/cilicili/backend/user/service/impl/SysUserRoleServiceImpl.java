package site.cilicili.backend.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.user.domain.dto.RemoveRoleUserRequest;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;
import site.cilicili.backend.user.mapper.SysUserRoleMapper;
import site.cilicili.backend.user.service.SysUserRoleService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.Optional;

/**
 * (SysUserRole) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-31 14:58:48
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity>
        implements SysUserRoleService {

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
    public R queryAll(SysUserRoleEntity sysUserRole) {
        return R.ok().setData(baseMapper.queryAll(sysUserRole));
    }

    /**
     * 新增数据
     *
     * @param sysUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUserRoleEntity sysUserRole) {
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
    public R update(SysUserRoleEntity sysUserRole) {
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

    @Override
    public boolean insertSysUserRoleList(final List<SysUserRoleEntity> toList) {
        return baseMapper.insertSysUserRoleList(toList) > 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R removeRoleUser(final RemoveRoleUserRequest removeRoleUserRequest) {
        final QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<SysUserRoleEntity>()
                .eq("sys_role_role_code", removeRoleUserRequest.roleCode())
                .eq("sys_user_username", removeRoleUserRequest.username());
        return Optional.ofNullable(baseMapper.selectOne(queryWrapper))
                .filter(item -> remove(queryWrapper.ne("sys_role_role_code", "super-admin")))
                .map(item -> R.yes("Success"))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
