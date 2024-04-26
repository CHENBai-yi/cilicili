package site.cilicili.backend.log.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.backend.log.domain.pojo.SysLogOperationEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-17 21:12:56
 */

/**
 * (SysLogOperation)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:12:56
 */
@Mapper
public interface SysLogOperationMapper extends BaseMapper<SysLogOperationEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLogOperationEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysLogOperation 查询条件
     * @return 对象列表
     */
    List<SysLogOperationEntity> queryAll(SysLogOperationEntity sysLogOperation);

    /**
     * 统计总行数
     *
     * @param sysLogOperation 查询条件
     * @return 总行数
     */
    long count(SysLogOperationEntity sysLogOperation);

    @Override
    /**
     * 新增数据
     *
     * @param sysLogOperation 实例对象
     * @return 影响行数
     */
    int insert(SysLogOperationEntity sysLogOperation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogOperationEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysLogOperationEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogOperationEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysLogOperationEntity> entities);

    /**
     * 修改数据
     *
     * @param sysLogOperation 实例对象
     * @return 影响行数
     */
    int update(SysLogOperationEntity sysLogOperation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<QueryLogResponse.LogOperation> queryLogOperationList(QueryLogRequest queryLogRequest);

    Integer countByParam(QueryLogRequest queryLogRequest);
}
