package site.cilicili.backend.log.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.backend.log.domain.pojo.SysLogOperationEntity;
import site.cilicili.backend.log.mapper.SysLogOperationMapper;
import site.cilicili.backend.log.service.SysLogOperationService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysLogOperation) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:12:57
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysLogOperationService")
public class SysLogOperationServiceImpl extends ServiceImpl<SysLogOperationMapper, SysLogOperationEntity>
        implements SysLogOperationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Long id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param sysLogOperation 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysLogOperationEntity sysLogOperation) {
        return R.ok().setData(baseMapper.queryAll(sysLogOperation));
    }

    /**
     * 新增数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysLogOperationEntity sysLogOperation) {
        baseMapper.insert(sysLogOperation);
        return R.ok().setData(sysLogOperation);
    }

    /**
     * 修改数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysLogOperationEntity sysLogOperation) {
        baseMapper.update(sysLogOperation);
        return R.ok().setData(this.queryById(sysLogOperation.getId()));
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
    public R getLogOperationList(final QueryLogRequest queryLogRequest) {
        Integer total = baseMapper.countByParam(queryLogRequest);
        return Optional.ofNullable(baseMapper.queryLogOperationList(queryLogRequest))
                .map(userList -> R.yes("Success")
                        .setData(QueryLogResponse.builder()
                                .total(total)
                                .page(queryLogRequest.page())
                                .pageSize(queryLogRequest.pageSize())
                                .records(userList)
                                .build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
