package site.cilicili.backend.todo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryParamResponse;
import site.cilicili.backend.todo.domain.dto.GetTodoListRequest;
import site.cilicili.backend.todo.domain.pojo.SysTodoEntity;
import site.cilicili.backend.todo.mapper.SysTodoMapper;
import site.cilicili.backend.todo.service.SysTodoService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysTodo) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-26 20:16:40
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysTodoService")
public class SysTodoServiceImpl extends ServiceImpl<SysTodoMapper, SysTodoEntity> implements SysTodoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    @Transactional(readOnly = true)
    public R queryById(SysTodoEntity id) {
        return Optional.ofNullable(baseMapper.queryByNoticeId(id.getId()))
                .map(record -> R.yes("查找成功.").setRecords(record))
                .orElseThrow(() -> new AppException("查询失败!"));
    }

    /**
     * 全查询
     *
     * @param sysTodo 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysTodoEntity sysTodo) {
        return R.ok().setData(baseMapper.queryAll(sysTodo));
    }

    /**
     * 新增待办
     *
     * @param sysTodo 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R insert(SysTodoEntity sysTodo) {
        return Optional.ofNullable(sysTodo)
                .filter(sysTodoEntity -> baseMapper.insert(sysTodoEntity) > 0)
                .map(r -> R.yes("添加成功."))
                .orElseThrow(() -> new AppException("添加待办事件失败！"));
    }

    /**
     * 修改数据
     *
     * @param authUserDetails
     * @param sysTodo         实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R update(final AuthUserDetails authUserDetails, SysTodoEntity sysTodo) {
        return Optional.ofNullable(authUserDetails)
                .filter(auth -> baseMapper.update(sysTodo) > 0)
                .map(auth -> R.yes(String.format("%1$s编辑成功.", auth.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Long id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Override
    @Transactional(readOnly = true)
    public R getTodoList(final GetTodoListRequest getTodoListRequest) {
        return Optional.ofNullable(baseMapper.selectTodoListByParam(getTodoListRequest))
                .map(records -> R.yes("Success.")
                        .setData(NoticeListQueryParamResponse.builder()
                                .records(records)
                                .total(records.size())
                                .page(getTodoListRequest.page())
                                .pageSize(getTodoListRequest.pageSize())
                                .build()))
                .orElse(R.no("Fail."));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteByTolistId(final AuthUserDetails authUserDetails, final SysTodoEntity id) {
        return Optional.ofNullable(authUserDetails)
                .filter(auth -> removeById(id))
                .map(auth -> R.yes(String.format("%1$s删除成功.", auth.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
