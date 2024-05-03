package site.cilicili.backend.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-25 15:55:22
 */

/**
 * (SysNoticeToUser)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:22
 */
@Mapper
public interface SysNoticeToUserMapper extends BaseMapper<SysNoticeToUserEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param noticeId 主键
     * @return 实例对象
     */
    SysNoticeToUserEntity queryById(String noticeId);

    /**
     * 查询指定行数据
     *
     * @param sysNoticeToUser 查询条件
     * @return 对象列表
     */
    List<SysNoticeToUserEntity> queryAll(SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 统计总行数
     *
     * @param sysNoticeToUser 查询条件
     * @return 总行数
     */
    long count(SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 影响行数
     */
    int insert(SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeToUserEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNoticeToUserEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeToUserEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNoticeToUserEntity> entities);

    /**
     * 修改数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 影响行数
     */
    int update(SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 通过主键删除数据
     *
     * @param noticeId 主键
     * @return 影响行数
     */
    int deleteById(String noticeId);

    SysNoticeToUserEntity queryNoticeByUser(@Param("noticeId") String noticeId, @Param("username") String username);

    int updateNoticeByUser(SysNoticeToUserEntity sysNoticeToUserEntity);
}
