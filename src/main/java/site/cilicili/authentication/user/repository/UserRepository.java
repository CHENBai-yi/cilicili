package site.cilicili.authentication.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import site.cilicili.authentication.user.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author BaiYiChen
 */
@Mapper
public interface UserRepository extends BaseMapper<UserEntity> {
    @Select("SELECT u FROM UserEntity u WHERE u.username = #{username} OR u.email = #{email}")
    List<UserEntity> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);

  /*  @Select("SELECT u FROM UserEntity u WHERE u.username = #{username} OR u.email = #{email}")
    List<UserEntity> findByUsernameOrEmail(@Param("username") String username, @Param("email") String email);*/

    @Select("SELECT u.* FROM sys_user u WHERE u.email = #{email}")
    Optional<UserEntity> findByEmail(@Param("email") String email);

    @Select(
            "SELECT u.*,r.sys_role_role_code as roleCode FROM  sys_user u JOIN sys_user_role r on u.username=r.sys_user_username WHERE u.username = #{username} OR u.email = #{email}")
    Optional<UserEntity> findByUsername(@Param("username") String username, @Param("email") String email);

    @Select(
            "SELECT u.*,r.sys_role_role_code as roleCode,l.token  FROM sys_user u JOIN sys_user_role r on u.username=r.sys_user_username join sys_user_online l on u.username=l.username WHERE u.username = #{username}")
    Optional<UserEntity> findByOnlineUsername(@Param("username") String username);

    @Select("SELECT sys_button_button_code FROM sys_role_button u WHERE u.sys_role_role_code = #{roleCode}")
    List<String> findRoleButtonByRoleCode(@Param("roleCode") String roleCode);
}
