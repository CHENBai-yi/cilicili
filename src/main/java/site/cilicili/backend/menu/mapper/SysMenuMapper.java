package site.cilicili.backend.menu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.menu.domain.dto.GetMenuListRequest;
import site.cilicili.backend.menu.domain.dto.GetUserMenuResponse;
import site.cilicili.backend.menu.domain.dto.SysMenuDto;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;

import java.util.List;

/**
 * (SysMenu)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:01:58
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysMenuEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysMenu 查询条件
     * @return 对象列表
     */
    List<SysMenuEntity> queryAll(SysMenuEntity sysMenu);

    /**
     * 统计总行数
     *
     * @param sysMenu 查询条件
     * @return 总行数
     */
    long count(SysMenuEntity sysMenu);

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int insert(SysMenuEntity sysMenu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysMenuEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysMenuEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysMenuEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysMenuEntity> entities);

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 影响行数
     */
    int update(SysMenuEntity sysMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysMenuDto.Records> getRoleMenuList(GetMenuListRequest sysMenuListRequest);

    GetUserMenuResponse getUserMenu(String username);
}
