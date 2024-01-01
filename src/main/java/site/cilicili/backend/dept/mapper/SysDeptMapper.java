package site.cilicili.backend.dept.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.dept.domain.dto.GetDeptListRequest;
import site.cilicili.backend.dept.domain.dto.SysDeptDto;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2023-12-30 16:36:33
 */

/**
 * (SysDept)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:33
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDeptEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDeptEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysDept 查询条件
     * @return 对象列表
     */
    List<SysDeptEntity> queryAll(SysDeptEntity sysDept);

    /**
     * 统计总行数
     *
     * @param sysDept 查询条件
     * @return 总行数
     */
    long count(SysDeptEntity sysDept);

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 影响行数
     */
    int insert(SysDeptEntity sysDept);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDeptEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysDeptEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysDeptEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysDeptEntity> entities);

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 影响行数
     */
    int update(SysDeptEntity sysDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<SysDeptDto.DeptListDto> getDeptList(GetDeptListRequest deptListRequest);
}


