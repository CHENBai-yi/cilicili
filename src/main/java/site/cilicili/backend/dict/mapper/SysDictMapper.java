package site.cilicili.backend.dict.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.dict.domain.dto.SysDictDto;
import site.cilicili.backend.dict.domain.pojo.SysDictEntity;

import java.util.List;

/**
 * (SysDict)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 12:44:33
 */
@Mapper
public interface SysDictMapper extends BaseMapper<SysDictEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDictEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysDict 查询条件
     * @return 对象列表
     */
    List<SysDictEntity> queryAll(SysDictEntity sysDict);

    /**
     * 统计总行数
     *
     * @param sysDict 查询条件
     * @return 总行数
     */
    long count(SysDictEntity sysDict);

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 影响行数
     */
    int insert(SysDictEntity sysDict);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDictEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDictEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDictEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysDictEntity> entities);

    /**
     * 修改数据
     *
     * @param sysDict 实例对象
     * @return 影响行数
     */
    int update(SysDictEntity sysDict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysDictDto.Records> queryDictAll();

    SysDictDto.Records queryDictById(Long id);
}
