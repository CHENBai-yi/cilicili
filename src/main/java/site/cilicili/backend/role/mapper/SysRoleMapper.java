package site.cilicili.backend.role.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.role.domain.dto.RoleListQueryParam;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;

import java.util.List;

/**
 * (SysRole)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:21
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysRoleEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysRole 查询条件
     * @return 对象列表
     */
    List<SysRoleEntity> queryAll(SysRoleEntity sysRole);

    /**
     * 统计总行数
     *
     * @param sysRole 查询条件
     * @return 总行数
     */
    long count(SysRoleEntity sysRole);

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int insert(SysRoleEntity sysRole);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysRoleEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysRoleEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysRoleEntity> entities);

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 影响行数
     */
    int update(SysRoleEntity sysRole);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysRoleDto.Records> queryRoleListByParam(RoleListQueryParam queryParam);
}


