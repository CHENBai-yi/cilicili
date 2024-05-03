package site.cilicili.backend.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.backend.log.domain.pojo.SysLogLoginEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-17 21:11:43
 */

/**
 * (SysLogLogin)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:43
 */
@Mapper
public interface SysLogLoginMapper extends BaseMapper<SysLogLoginEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLogLoginEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysLogLogin 查询条件
     * @return 对象列表
     */
    List<SysLogLoginEntity> queryAll(SysLogLoginEntity sysLogLogin);

    /**
     * 统计总行数
     *
     * @param sysLogLogin 查询条件
     * @return 总行数
     */
    long count(SysLogLoginEntity sysLogLogin);

    @Override
    /**
     * 新增数据
     *
     * @param sysLogLogin 实例对象
     * @return 影响行数
     */
    int insert(SysLogLoginEntity sysLogLogin);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogLoginEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysLogLoginEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogLoginEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysLogLoginEntity> entities);

    /**
     * 修改数据
     *
     * @param sysLogLogin 实例对象
     * @return 影响行数
     */
    int update(SysLogLoginEntity sysLogLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<QueryLogResponse.LogLogin> queryLogLoginList(QueryLogRequest queryLogRequest);

    int insertLogLogin(SysLogLoginEntity sysLogLogin);

    Integer countByParam(QueryLogRequest queryLogRequest);
}
