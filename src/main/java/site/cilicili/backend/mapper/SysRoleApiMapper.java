package site.cilicili.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.domain.pojo.SysRoleApi;

import java.util.List;

/**
 * (SysRoleApi)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@Mapper
public interface SysRoleApiMapper extends BaseMapper<SysRoleApi> {
    SysRoleApi queryById();

    /**
     * 查询指定行数据
     *
     * @param sysRoleApi 查询条件
     * @return 对象列表
     */
    List<SysRoleApi> queryAll(SysRoleApi sysRoleApi);

    /**
     * 统计总行数
     *
     * @param sysRoleApi 查询条件
     * @return 总行数
     */
    long count(SysRoleApi sysRoleApi);

    /**
     * 新增数据
     *
     * @param sysRoleApi 实例对象
     * @return 影响行数
     */
    int insert(SysRoleApi sysRoleApi);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleApi> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleApi> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleApi> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRoleApi> entities);

    /**
     * 修改数据
     *
     * @param sysRoleApi 实例对象
     * @return 影响行数
     */
    int update(SysRoleApi sysRoleApi);

    int deleteById();
}


