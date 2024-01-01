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

    Optional<UserEntity> findByEmail(String email);

    @Select("SELECT * FROM sys_user u WHERE u.username = #{username}")
    Optional<UserEntity> findByUsername(@Param("username") String username);
}
