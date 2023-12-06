package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysNoticeToUser;

import java.util.List;

/**
 * (SysNoticeToUser)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@Mapper
public interface SysNoticeToUserMapper extends BaseMapper<SysNoticeToUser> {
    /**
     * 通过ID查询单条数据
     *
     * @param 主键
     * @return 实例对象
     */
    SysNoticeToUser queryById();

    /**
     * 查询指定行数据
     *
     * @param sysNoticeToUser 查询条件
     * @return 对象列表
     */
    List<SysNoticeToUser> queryAll(SysNoticeToUser sysNoticeToUser);

    /**
     * 统计总行数
     *
     * @param sysNoticeToUser 查询条件
     * @return 总行数
     */
    long count(SysNoticeToUser sysNoticeToUser);

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 影响行数
     */
    int insert(SysNoticeToUser sysNoticeToUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeToUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNoticeToUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeToUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNoticeToUser> entities);

    /**
     * 修改数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 影响行数
     */
    int update(SysNoticeToUser sysNoticeToUser);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 影响行数
     */
    int deleteById();
}


