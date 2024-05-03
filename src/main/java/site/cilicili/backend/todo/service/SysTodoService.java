package site.cilicili.backend.todo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.todo.domain.dto.GetTodoListRequest;
import site.cilicili.backend.todo.domain.pojo.SysTodoEntity;
import site.cilicili.common.util.R;

/**
 * (SysTodo)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-26 20:16:40
 */
public interface SysTodoService extends IService<SysTodoEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final SysTodoEntity id);

    /**
     * 全查询
     *
     * @param sysTodo 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysTodoEntity sysTodo);

    /**
     * 新增数据
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    R insert(final SysTodoEntity sysTodo);

    /**
     * 修改数据
     *
     * @param authUserDetails
     * @param sysTodo         实例对象
     * @return 实例对象
     */
    R update(final AuthUserDetails authUserDetails, final SysTodoEntity sysTodo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getTodoList(GetTodoListRequest getTodoListRequest);

    R deleteByTolistId(AuthUserDetails authUserDetails, SysTodoEntity id);
}
