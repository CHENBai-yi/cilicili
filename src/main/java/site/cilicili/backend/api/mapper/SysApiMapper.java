package site.cilicili.backend.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.api.domain.dto.GetApiListRequest;
import site.cilicili.backend.api.domain.dto.SysApiDto;
import site.cilicili.backend.api.domain.pojo.SysApiEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 15:21:42
 */

/**
 * (SysApi)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:42
 */
@Mapper
public interface SysApiMapper extends BaseMapper<SysApiEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysApiEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysApi 查询条件
     * @return 对象列表
     */
    List<SysApiEntity> queryAll(SysApiEntity sysApi);

    /**
     * 统计总行数
     *
     * @param sysApi 查询条件
     * @return 总行数
     */
    long count(SysApiEntity sysApi);

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 影响行数
     */
    int insert(SysApiEntity sysApi);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysApiEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysApiEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysApiEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysApiEntity> entities);

    /**
     * 修改数据
     *
     * @param sysApi 实例对象
     * @return 影响行数
     */
    int update(SysApiEntity sysApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysApiDto.Records> getApiList(GetApiListRequest apiListRequest);
}


