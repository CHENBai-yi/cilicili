package site.cilicili.backend.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.backend.user.domain.dto.QueryUserOnlineListRequest;
import site.cilicili.backend.user.domain.pojo.SysUserOnlineEntity;
import site.cilicili.backend.user.mapper.SysUserOnlineMapper;
import site.cilicili.backend.user.service.SysUserOnlineService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysUserOnline) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-18 20:39:32
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysUserOnlineService")
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnlineEntity>
        implements SysUserOnlineService {

    /**
     * 全查询
     *
     * @param sysUserOnline 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysUserOnlineEntity sysUserOnline) {
        return R.ok().setData(baseMapper.queryAll(sysUserOnline));
    }

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUserOnlineEntity sysUserOnline) {
        baseMapper.insert(sysUserOnline);
        return R.ok().setData(sysUserOnline);
    }

    /**
     * 修改数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysUserOnlineEntity sysUserOnline) {
        baseMapper.update(sysUserOnline);
        return R.ok().setData(this.getOne(
                new QueryWrapper<SysUserOnlineEntity>().eq("username", sysUserOnline.getUsername())));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R kickOnlineUser(final KickOnlineUserRequest kickOnlineUserRequest) {
        final QueryWrapper<SysUserOnlineEntity> queryWrapper = new QueryWrapper<SysUserOnlineEntity>()
                .eq("username", kickOnlineUserRequest.username())
                .or()
                .eq("token", kickOnlineUserRequest.token());
        return Optional.ofNullable(this.getOne(queryWrapper))
                .filter(sysUserOnlineEntity -> this.remove(queryWrapper))
                .map(sysUserOnlineEntity -> R.yes(String.format("%1$s 下线成功.", sysUserOnlineEntity.getUsername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(readOnly = true)
    public R getUserOnlineList(final QueryUserOnlineListRequest queryUserOnlineListRequest) {
        return Optional.ofNullable(baseMapper.queryUserOnlineList(queryUserOnlineListRequest))
                .map(userList -> R.yes("Success")
                        .setData(QueryLogResponse.builder()
                                .total(userList.size())
                                .page(queryUserOnlineListRequest.page())
                                .pageSize(queryUserOnlineListRequest.pageSize())
                                .records(userList)
                                .build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean insertOrUpdate(final String username, final String encode) {
        return baseMapper.insertOrUpdate(username, encode) > 0;
    }

    @Override
    @Transactional(readOnly = true)
    public SysUserOnlineEntity queryById(final String id, String token) {
        return baseMapper.queryById(id, token);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean dropOnline(String username) {
        return baseMapper.dropOnline(username) > 0;
    }

    @Override
    public boolean removeByNameOrToken(final String username, final String token) {
        return baseMapper.deleteById(username, token) > 0;
    }
}
