package site.cilicili.frontend.subjects.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.subjects.domain.dto.GetSubjectListRequest;
import site.cilicili.frontend.subjects.domain.pojo.SubjectsEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:39
 */

/**
 * (Subjects)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:39
 */
@Mapper
public interface SubjectsMapper extends BaseMapper<SubjectsEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectsEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param subjects 查询条件
     * @return 对象列表
     */
    List<SubjectsEntity> queryAll(SubjectsEntity subjects);

    /**
     * 统计总行数
     *
     * @param subjects 查询条件
     * @return 总行数
     */
    long count(SubjectsEntity subjects);

    /**
     * 新增数据
     *
     * @param subjects 实例对象
     * @return 影响行数
     */
    int insert(SubjectsEntity subjects);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectsEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SubjectsEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SubjectsEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SubjectsEntity> entities);

    /**
     * 修改数据
     *
     * @param subjects 实例对象
     * @return 影响行数
     */
    int update(SubjectsEntity subjects);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<SubjectsEntity> getSubjectList(GetSubjectListRequest subjects);
}
