package site.cilicili.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.user.domain.dto.*;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysUser)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-14 21:53:23
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Long id);

    /**
     * 全查询
     *
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysUserEntity sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    R insert(final SysUserEntity sysUser);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    R update(final SysUserEntity sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getUserList(GetUserListRequest getUserListRequest);

    R addUser(AddUserRequest.Records addUserRequest);

    R resetPassword(ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest);

    R deleteUserById(ResetPasswordAndDeleteUserRequest resetPasswordAndDeleteUserRequest);

    R editUser(AddUserRequest.Records editUserRequest);

    R queryUserById(final ResetPasswordAndDeleteUserRequest id);

    R changeNickname(AuthUserDetails authUserDetails, ChangeNicknameRequest changeNicknameRequest);

    R changePassword(AuthUserDetails authUserDetails, ChangePasswordRequest changePasswordRequest);

    List<String> getUsernameList();

    R getTeacherList(final SysRoleEntity sysRole);

    R changeInfo(AuthUserDetails authUserDetails, SysUserEntity sysUserEntity);
}
