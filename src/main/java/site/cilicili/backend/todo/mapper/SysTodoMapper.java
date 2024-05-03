package site.cilicili.backend.todo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.todo.domain.dto.GetTodoListRequest;
import site.cilicili.backend.todo.domain.dto.TodoListResponse;
import site.cilicili.backend.todo.domain.pojo.SysTodoEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-26 20:16:39
 */

/**
 * (SysTodo)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-26 20:16:39
 */
@Mapper
public interface SysTodoMapper extends BaseMapper<SysTodoEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysTodoEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysTodo 查询条件
     * @return 对象列表
     */
    List<SysTodoEntity> queryAll(SysTodoEntity sysTodo);

    /**
     * 统计总行数
     *
     * @param sysTodo 查询条件
     * @return 总行数
     */
    long count(SysTodoEntity sysTodo);

    /**
     * 新增数据
     *
     * @param sysTodo 实例对象
     * @return 影响行数
     */
    int insert(SysTodoEntity sysTodo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysTodoEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysTodoEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysTodoEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysTodoEntity> entities);

    /**
     * 修改数据
     *
     * @param sysTodo 实例对象
     * @return 影响行数
     */
    int update(SysTodoEntity sysTodo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<TodoListResponse.Record> selectTodoListByParam(GetTodoListRequest getTodoListRequest);

    TodoListResponse.Record queryByNoticeId(Long id);
}
