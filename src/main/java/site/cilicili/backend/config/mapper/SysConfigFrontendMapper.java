package site.cilicili.backend.config.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.dto.SysConfigFrontendDto;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;

import java.util.List;

/**
 * (SysConfigFrontend)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
@Mapper
public interface SysConfigFrontendMapper extends BaseMapper<SysConfigFrontendEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysConfigFrontendEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysConfigFrontend 查询条件
     * @return 对象列表
     */
    List<SysConfigFrontendEntity> queryAll(SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 统计总行数
     *
     * @param sysConfigFrontend 查询条件
     * @return 总行数
     */
    long count(SysConfigFrontendEntity sysConfigFrontend);

    @Override
    /**
     * 新增数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 影响行数
     */
    int insert(SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigFrontendEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysConfigFrontendEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysConfigFrontendEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysConfigFrontendEntity> entities);

    /**
     * 修改数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 影响行数
     */
    int update(SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysConfigFrontendDto.Records> queryConfigFrontAll();

    List<SysConfigFrontendDto.Records> queryConfigFrontendList(QueryConfigRequest queryFrontendRequest);

    Integer countByParam(QueryConfigRequest queryFrontendRequest);
}
