package site.cilicili.backend.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.api.service.SysRoleApiService;
import site.cilicili.backend.role.domain.dto.*;
import site.cilicili.backend.role.domain.pojo.SysRoleButtonEntity;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.role.domain.pojo.SysRoleMenuEntity;
import site.cilicili.backend.role.mapper.SysRoleMapper;
import site.cilicili.backend.role.service.SysRoleButtonService;
import site.cilicili.backend.role.service.SysRoleMenuService;
import site.cilicili.backend.role.service.SysRoleService;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.domain.pojo.SysUserRoleEntity;
import site.cilicili.backend.user.service.SysUserRoleService;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * (SysRole) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:21
 */
@RequiredArgsConstructor
@Service("sysRoleService")
@CacheConfig(cacheNames = {"SysMenu", "SysRole"})
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {
    public final SysUserRoleService sysUserRoleService;
    private final SysRoleButtonService sysRoleButtonService;
    private final SysRoleMenuService sysRoleMenuService;
    private final SysRoleApiService sysRoleApiService;
    private final ThreadPoolExecutor threadPoolTaskExecutor;
    private final SysUserService sysUserService;

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
    public R queryAll(SysRoleEntity sysRole) {
        return R.ok().setData(baseMapper.queryAll(sysRole));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleEntity sysRole) {
        Optional.ofNullable(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", sysRole.getRoleCode()))).ifPresent(item -> {
            throw new AppException(Error.DUPLICATED_USER);
        });
        try {
            if (saveOrUpdate(sysRole)) {
                return R.yes(String.format("%1$s添加成功.", sysRole.getRoleName()));
            }
        } catch (DuplicateKeyException e) {
            if (baseMapper.insertOrUpdateBatch(List.of(sysRole)) > 0) {
                return R.yes(String.format("%1$s添加成功.", sysRole.getRoleName()));
            }
        }
        return R.no(String.format("%1$s添加失败.", sysRole.getRoleName()));
    }

    private boolean saveOrUpdateRole(final SysRoleEntity sysRole) {
        return baseMapper.saveOrUpdateRole(sysRole) > 0;
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleEntity sysRole) {
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

    @Override
    public R queryRoleListByParam(final RoleListQueryParam queryParam) {
        return Optional.ofNullable(queryParam).map(queryParam1 -> Optional.ofNullable(queryParam1.getId()).map(id -> Optional.ofNullable(baseMapper.queryRoleListById(id)).map(records -> R.yes("查找成功.").setData(SysRoleDto.builder().records(records).build())).orElse(R.yes("查找成功.").setData(SysRoleDto.builder().build()))).orElse(Optional.ofNullable(baseMapper.queryRoleListByParam(queryParam)).map(records -> R.yes("Success.").setData(SysRoleDto.builder().page(queryParam.getPage()).pageSize(queryParam.getPageSize()).total(records.size()).records(records).build())).orElse(R.yes("Success").setData(SysRoleDto.builder().build())))).orElseGet(() -> Optional.ofNullable(baseMapper.queryRoleListByParam(queryParam)).map(records -> R.yes("Success.").setData(SysRoleDto.builder().total(records.size()).records(records).build())).orElse(R.yes("Success").setData(SysRoleDto.builder().build())));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R editRole(final EditRequest sysRole) {
        return Optional.ofNullable(baseMapper.selectById(sysRole.id())).map(sysRole1 -> {
            sysRole1.setRoleName(sysRole.roleName());
            sysRole1.setMemo(sysRole.memo());
            sysRole1.setSort(sysRole.sort());
            if (updateById(sysRole1)) {
                return R.yes("修改成功");
            } else {
                return R.no("修改失败");
            }
        }).orElse(R.no("此角色信息不存在."));
    }

    @Override
    @Transactional
    public R deleteRoleById(final EditRequest sysRole) {
        return Optional.ofNullable(baseMapper.selectById(sysRole.id())).map(sysRole1 -> {
            if (sysRole1.getId() != 1L && !"super-admin".equals(sysRole1.getRoleCode())) {
                sysRole1.setLogicalDelete(0);
                if (baseMapper.update(sysRole1) > 0) {
                    return R.yes("删除成功.");
                } else {
                    return R.no("删除失败.");
                }
            } else {
                return R.no("干嘛删管理员,你想做什!!!?");
            }
        }).orElse(R.no("没有该角色"));
    }

    @Override
    @Transactional
    public R editRoleDeptDataPermission(final EditRoleDeptDataPermissionRequest editRoleDeptDataPermissionRequest) {
        return Optional.ofNullable(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", editRoleDeptDataPermissionRequest.roleCode()))).map(sysRole1 -> {
            sysRole1.setDeptDataPermissionType(editRoleDeptDataPermissionRequest.deptDataPermissionType());
            sysRole1.setDeptDataPermissionCustom(editRoleDeptDataPermissionRequest.deptDataPermissionCustom());
            if (baseMapper.update(sysRole1) > 0) {
                return R.yes(String.format("%1$s编辑角色部门数据权限成功.", editRoleDeptDataPermissionRequest.roleCode()));
            } else {
                return R.no("修改失败");
            }
        }).orElse(R.no(String.format("%1$s此角色信息不存在.", editRoleDeptDataPermissionRequest.roleCode())));
    }

    @Override
    @Transactional(readOnly = true)
    public R queryUserByRole(final QueryUserByRoleRequest queryUserByRoleRequest) {
        return Optional.ofNullable(baseMapper.queryUserByRole(queryUserByRoleRequest)).map(users -> R.yes("Success").setData("records", users)).orElse(R.no("Fail"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @CacheEvict(key = "#root.args[0].roleCode")
    public R editRoleMenu(final EditRoleMenuRequest editRoleMenuRequest) {
        return Optional.ofNullable(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", editRoleMenuRequest.roleCode()))).map(sysRole -> {
            if ((!editRoleMenuRequest.roleMenu().isEmpty() && (sysRoleMenuService.remove(new QueryWrapper<SysRoleMenuEntity>().eq("sys_role_role_code", sysRole.getRoleCode())) | sysRoleMenuService.saveBatch(editRoleMenuRequest.roleMenu()))) | (!editRoleMenuRequest.roleButton().isEmpty() && (sysRoleButtonService.remove(new QueryWrapper<SysRoleButtonEntity>().eq("sys_role_role_code", sysRole.getRoleCode())) | sysRoleButtonService.saveBatch(editRoleMenuRequest.roleButton())))) {
                sysRole.setDefaultPage(editRoleMenuRequest.defaultPage());
                baseMapper.update(sysRole);
                return R.yes(String.format("%1$s编辑角色菜单成功.", editRoleMenuRequest.roleCode()));
            } else {
                throw new AppException(Error.COMMON_EXCEPTION);
            }
        }).orElse(R.no("角色不存在."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R editRoleApi(final EditRoleApiRequest editRoleApiRequest) {
        return Optional.ofNullable(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", editRoleApiRequest.roleCode()))).map(sysRole -> {
            if (!editRoleApiRequest.roleApi().isEmpty() && sysRoleApiService.saveOrUpdateBatch(editRoleApiRequest.roleApi())) {
                return R.yes(String.format("%1$s编辑角色菜单成功.", editRoleApiRequest.roleCode()));
            } else {
                throw new AppException(Error.COMMON_EXCEPTION);
            }
        }).orElse(R.no("角色不存在."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R addRoleUser(final AddRoleUserRequest addRoleUserRequest) {
        return Optional.ofNullable(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", addRoleUserRequest.roleCode()))).map(sysRole -> {
            if (!addRoleUserRequest.username().isEmpty() && (sysUserRoleService.insertSysUserRoleList(addRoleUserRequest.username().stream().map(item -> new SysUserRoleEntity(addRoleUserRequest.roleCode(), item)).toList())) & sysUserService.saveOrUpdateBatch(addRoleUserRequest.username().stream().map(name -> {
                final SysUserEntity user = sysUserService.getOne(new QueryWrapper<SysUserEntity>().eq("username", name));
                user.setSort(sysRole.getSort());
                return user;
            }).collect(Collectors.toList()))) {
                return R.yes(String.format("%1$s添加角色用户成功.", addRoleUserRequest.roleCode()));
            } else {
                throw new AppException(Error.COMMON_EXCEPTION);
            }
        }).orElse(R.no("角色不存在."));
    }
}
