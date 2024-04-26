package site.cilicili.backend.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.user.domain.dto.QueryUserOnlineListRequest;
import site.cilicili.backend.user.domain.dto.QueryUserOnlineListResponse;
import site.cilicili.backend.user.domain.pojo.SysUserOnlineEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-18 20:39:30
 */

/**
 * (SysUserOnline)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-18 20:39:30
 */
@Mapper
public interface SysUserOnlineMapper extends BaseMapper<SysUserOnlineEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id    主键
     * @param token
     * @return 实例对象
     */
    SysUserOnlineEntity queryById(@Param("id") String id, @Param("t") final String token);

    /**
     * 查询指定行数据
     *
     * @param sysUserOnline 查询条件
     * @return 对象列表
     */
    List<SysUserOnlineEntity> queryAll(SysUserOnlineEntity sysUserOnline);

    /**
     * 统计总行数
     *
     * @param sysUserOnline 查询条件
     * @return 总行数
     */
    long count(SysUserOnlineEntity sysUserOnline);

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 影响行数
     */
    @Override
    int insert(SysUserOnlineEntity sysUserOnline);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserOnlineEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysUserOnlineEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysUserOnlineEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysUserOnlineEntity> entities);

    /**
     * 修改数据
     *
     * @param sysUserOnline 实例对象
     * @return 影响行数
     */
    int update(SysUserOnlineEntity sysUserOnline);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("username") String id, @Param("tokens") String token);

    List<QueryUserOnlineListResponse.OnlineList> queryUserOnlineList(
            QueryUserOnlineListRequest queryUserOnlineListRequest);

    int insertOrUpdate(@Param("username") String username, @Param("token") String token);

    int dropOnline(final String username);

    Integer countByParam(QueryUserOnlineListRequest queryUserOnlineListRequest);
}
