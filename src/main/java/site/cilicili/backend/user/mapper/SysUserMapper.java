package site.cilicili.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.user.domain.dto.GetUserListRequest;
import site.cilicili.backend.user.domain.dto.UserListDto;
import site.cilicili.backend.user.domain.pojo.SysUserEntity;

import java.util.List;

/**
 * (SysUser)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 21:53:23
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysUserEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysUser 查询条件
     * @return 对象列表
     */
    List<SysUserEntity> queryAll(SysUserEntity sysUser);

    /**
     * 统计总行数
     *
     * @param sysUser 查询条件
     * @return 总行数
     */
    long count(SysUserEntity sysUser);

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int insert(SysUserEntity sysUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserEntity> entities);

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 影响行数
     */
    int update(SysUserEntity sysUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<UserListDto.Records> getUserList(GetUserListRequest getUserListRequest);

    SysUserEntity selectOneSysUser(String username);

    UserListDto.Records getUserById(Long id);

    List<String> getUsernameList();
}
