package site.cilicili.backend.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.dto.SysConfigBackendDto;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;

import java.util.List;

/**
 * (SysConfigBackend)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:42
 */
@Mapper
public interface SysConfigBackendMapper extends BaseMapper<SysConfigBackendEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysConfigBackendEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysConfigBackend 查询条件
     * @return 对象列表
     */
    List<SysConfigBackendEntity> queryAll(SysConfigBackendEntity sysConfigBackend);

    /**
     * 统计总行数
     *
     * @param sysConfigBackend 查询条件
     * @return 总行数
     */
    long count(SysConfigBackendEntity sysConfigBackend);

    /**
     * 新增数据
     *
     * @param sysConfigBackend 实例对象
     * @return 影响行数
     */
    int insert(SysConfigBackendEntity sysConfigBackend);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigBackendEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysConfigBackendEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigBackendEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysConfigBackendEntity> entities);

    /**
     * 修改数据
     *
     * @param sysConfigBackend 实例对象
     * @return 影响行数
     */
    int update(SysConfigBackendEntity sysConfigBackend);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysConfigBackendDto.Records> queryConfigBackendAll();

    List<SysConfigBackendDto.Records> getConfigBackendList(QueryConfigRequest queryBackRequest);

    Integer countByParam(QueryConfigRequest queryBackRequest);
}
