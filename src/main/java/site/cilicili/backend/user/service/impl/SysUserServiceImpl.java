package site.cilicili.backend.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.backend.config.service.SysConfigBackendService;
import site.cilicili.backend.dept.domain.pojo.SysDeptUserEntity;
import site.cilicili.backend.dept.service.SysDeptUserService;
import site.cilicili.backend.user.domain.dto.*;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.backend.user.mapper.SysUserMapper;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.Optional;

/**
 * (SysUser) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 21:53:23
 */
@RequiredArgsConstructor
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    public final SysDeptUserService sysDeptUserService;
    private final PasswordEncoder passwordEncoder;
    private final SysConfigBackendService sysConfigBackendService;

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
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysUserEntity sysUser) {
        return R.ok().setData(baseMapper.queryAll(sysUser));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUserEntity sysUser) {
        baseMapper.insert(sysUser);
        return R.ok().setData(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysUserEntity sysUser) {
        baseMapper.update(sysUser);
        return R.ok().setData(this.queryById(sysUser.getId()));
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
    @Transactional(readOnly = true)
    public R getUserList(final GetUserListRequest getUserListRequest) {
        return Optional.ofNullable(baseMapper.getUserList(getUserListRequest))
                .map(userList -> R.yes("Success")
                        .setData(UserListDto.builder()
                                .total(userList.size())
                                .page(getUserListRequest.page())
                                .pageSize(getUserListRequest.pageSize())
                                .records(userList)
                                .build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R addUser(final AddUserRequest.Records addUserRequest) {
        return Optional.ofNullable(baseMapper.selectOneSysUser(addUserRequest.getUsername()))
                .map(item -> R.no(String.format("%1$s用户已经存在", item.getUsername())))
                .orElseGet(() -> {
                    if (!addUserRequest.getDept().isEmpty()
                            && (sysDeptUserService.insertDeptUserList(addUserRequest.getDept().stream()
                            .map(item ->
                                    new SysDeptUserEntity(item.getDeptCode(), addUserRequest.getUsername()))
                            .toList()))) {
                        return Optional.ofNullable(sysConfigBackendService
                                        .getBaseMapper()
                                        .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                                .eq("config_item", "defaultPassword")))
                                .map(item -> {
                                    addUserRequest.setPassword(passwordEncoder.encode(item.getItemDefault()));
                                    baseMapper.insert(addUserRequest);
                                    return R.yes("Success");
                                })
                                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
                    } else {
                        throw new AppException(Error.COMMON_EXCEPTION);
                    }
                });
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R resetPassword(final ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest) {
        return Optional.ofNullable(baseMapper.selectById(resetPasswordAndDeleteUserRequest.id()))
                .map(sysUserEntity -> Optional.ofNullable(sysConfigBackendService
                                .getBaseMapper()
                                .selectOne(new QueryWrapper<SysConfigBackendEntity>()
                                        .eq("config_item", "defaultPassword")))
                        .map(item -> {
                            sysUserEntity.setPassword(passwordEncoder.encode(item.getItemDefault()));
                            if (saveOrUpdate(sysUserEntity)) {
                                return R.yes("重置密码成功.");
                            }
                            return R.no("密码重置失败.");
                        })
                        .orElse(R.no("未在系统中找到默认配置密码.")))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteUserById(final ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest) {
        return Optional.ofNullable(baseMapper.selectById(resetPasswordAndDeleteUserRequest.id()))
                .map(sysUserEntity -> {
                    sysUserEntity.setLogicalDelete(0);
                    if (!"SuperAdmin".equals(sysUserEntity.getRealName()) && baseMapper.update(sysUserEntity) > 0) {
                        return R.yes(String.format("%1$s删除成功.", sysUserEntity.getUsername()));
                    }
                    return R.no(String.format("%1$s删除失败.", sysUserEntity.getUsername()));
                })
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R editUser(final AddUserRequest.Records editUserRequest) {
        return Optional.ofNullable(baseMapper.selectOneSysUser(editUserRequest.getUsername()))
                .map(sysUserEntity -> {
                    if (!editUserRequest.getDept().isEmpty()
                            && (sysDeptUserService.remove(new QueryWrapper<SysDeptUserEntity>()
                            .eq("sys_user_username", sysUserEntity.getUsername()))
                            | (sysDeptUserService.updateDeptUserList(editUserRequest.getDept().stream()
                            .map(item -> new SysDeptUserEntity(
                                    item.getDeptCode(), editUserRequest.getUsername()))
                            .toList())
                            && updateById(editUserRequest)))) {
                        return R.yes(String.format("%1$s编辑成功.", sysUserEntity.getUsername()));
                    }
                    return R.no(String.format("%1$s编辑失败.", sysUserEntity.getUsername()));
                })
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    public R queryUserById(final ResetPasswordAndDeleteUserRequest id) {
        return Optional.ofNullable(baseMapper.getUserById(id.id()))
                .map(sysUserEntity -> R.yes("查找成功.").setRecords(sysUserEntity))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R changeNickname(final AuthUserDetails authUserDetails, final ChangeNicknameRequest changeNicknameRequest) {
        return Optional.ofNullable(baseMapper.selectById(authUserDetails.getId()))
                .filter(sysUserEntity -> {
                    sysUserEntity.setNickname(changeNicknameRequest.nickname());
                    return updateById(sysUserEntity);
                })
                .map(sysUserEntity -> R.yes(String.format("%1$s修改昵称成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R changePassword(final AuthUserDetails authUserDetails, final ChangePasswordRequest changePasswordRequest) {
        return Optional.ofNullable(baseMapper.selectById(authUserDetails.getId()))
                .filter(sysUserEntity ->
                        changePasswordRequest.new_password_1().equals(changePasswordRequest.new_password_2()))
                .filter(sysUserEntity ->
                        passwordEncoder.matches(changePasswordRequest.old_password(), sysUserEntity.getPassword()))
                .filter(sysUserEntity -> {
                    sysUserEntity.setPassword(passwordEncoder.encode(changePasswordRequest.new_password_1()));
                    return updateById(sysUserEntity);
                })
                .map(sysUserEntity -> R.yes(String.format("%1$s修改密码成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getUsernameList() {
        return baseMapper.getUsernameList();
    }
}
