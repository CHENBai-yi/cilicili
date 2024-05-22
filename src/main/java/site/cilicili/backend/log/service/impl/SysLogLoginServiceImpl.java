package site.cilicili.backend.log.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.log.domain.dto.DeleteLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.backend.log.domain.pojo.SysLogLoginEntity;
import site.cilicili.backend.log.mapper.SysLogLoginMapper;
import site.cilicili.backend.log.service.SysLogLoginService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * (SysLogLogin) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:43
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysLogLoginService")
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLoginEntity>
        implements SysLogLoginService {

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
     * @param sysLogLogin 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysLogLoginEntity sysLogLogin) {
        return R.ok().setData(baseMapper.queryAll(sysLogLogin));
    }

    /**
     * 新增数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R insert(SysLogLoginEntity sysLogLogin) {
        baseMapper.insertLogLogin(sysLogLogin);
        return R.ok().setData(sysLogLogin);
    }

    /**
     * 修改数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysLogLoginEntity sysLogLogin) {
        baseMapper.update(sysLogLogin);
        return R.ok().setData(this.queryById(sysLogLogin.getId()));
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
    public R getLogLoginList(final QueryLogRequest queryLogRequest) {
        Integer total = baseMapper.countByParam(queryLogRequest);
        return Optional.ofNullable(baseMapper.queryLogLoginList(queryLogRequest))
                .map(userList -> R.yes("Success")
                        .setData(QueryLogResponse.builder()
                                .total(total)
                                .page(queryLogRequest.page())
                                .pageSize(queryLogRequest.pageSize())
                                .records(userList)
                                .build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteLogLoginById(final AuthUserDetails authUserDetails, final DeleteLogRequest deleteLogRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> baseMapper.selectById(deleteLogRequest.id()))
                .filter(this::removeById)
                .map(sysDictEntity -> R.yes(String.format("%1$s删除成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    public List<Map<String, Object>> getIpData(final DateTime start, final Date end) {
        return baseMapper.getIpData(start, end);
    }

    @Override
    public List<Map<String, Object>> getUvData(final DateTime start, final Date end) {
        return baseMapper.getUvData(start, end);
    }
}
