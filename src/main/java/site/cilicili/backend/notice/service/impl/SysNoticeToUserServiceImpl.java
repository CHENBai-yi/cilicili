package site.cilicili.backend.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;
import site.cilicili.backend.notice.mapper.SysNoticeToUserMapper;
import site.cilicili.backend.notice.service.SysNoticeToUserService;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysNoticeToUser) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:22
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysNoticeToUserService")
public class SysNoticeToUserServiceImpl extends ServiceImpl<SysNoticeToUserMapper, SysNoticeToUserEntity>
        implements SysNoticeToUserService {

    /**
     * 全查询
     *
     * @param sysNoticeToUser 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysNoticeToUserEntity sysNoticeToUser) {
        return R.ok().setData(baseMapper.queryAll(sysNoticeToUser));
    }

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysNoticeToUserEntity sysNoticeToUser) {
        baseMapper.insert(sysNoticeToUser);
        return R.ok().setData(sysNoticeToUser);
    }

    /**
     * 修改数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysNoticeToUserEntity sysNoticeToUser) {
        baseMapper.update(sysNoticeToUser);
        return R.ok().setData(baseMapper.queryById(sysNoticeToUser.getNoticeId()));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean insertOrUpdateBatch(final List<SysNoticeToUserEntity> toList) {
        return baseMapper.insertOrUpdateBatch(toList) > 0;
    }

    @Override
    public SysNoticeToUserEntity queryById(final String noticeId) {
        return baseMapper.queryById(noticeId);
    }

    @Override
    @Transactional(readOnly = true)
    public SysNoticeToUserEntity queryNoticeByUser(final String noticeId, final String getusername) {
        return baseMapper.queryNoticeByUser(noticeId, getusername);
    }

    @Override
    public boolean updateNoticeByUser(final SysNoticeToUserEntity sysNoticeToUserEntity) {
        return baseMapper.updateNoticeByUser(sysNoticeToUserEntity) > 0;
    }

    /**
     * @param entity 实体对象
     * @return
     */
    @Override
    public boolean updateById(final SysNoticeToUserEntity entity) {
        return baseMapper.update(entity) > 0;
    }
}
