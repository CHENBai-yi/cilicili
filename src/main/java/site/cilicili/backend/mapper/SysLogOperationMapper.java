package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysLogOperation;

import java.util.List;

/**
 * (SysLogOperation)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:42
 */
@Mapper
public interface SysLogOperationMapper extends BaseMapper<SysLogOperation> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysLogOperation queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysLogOperation 查询条件
     * @return 对象列表
     */
    List<SysLogOperation> queryAll(SysLogOperation sysLogOperation);

    /**
     * 统计总行数
     *
     * @param sysLogOperation 查询条件
     * @return 总行数
     */
    long count(SysLogOperation sysLogOperation);

    /**
     * 新增数据
     *
     * @param sysLogOperation 实例对象
     * @return 影响行数
     */
    int insert(SysLogOperation sysLogOperation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogOperation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysLogOperation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysLogOperation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysLogOperation> entities);

    /**
     * 修改数据
     *
     * @param sysLogOperation 实例对象
     * @return 影响行数
     */
    int update(SysLogOperation sysLogOperation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}


