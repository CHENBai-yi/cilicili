package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysNoticeToUser;
import site.cilicili.backend.mapper.SysNoticeToUserMapper;
import site.cilicili.backend.service.SysNoticeToUserService;
import site.cilicili.common.util.R;

/**
 * (SysNoticeToUser)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@RequiredArgsConstructor
@Service("sysNoticeToUserService")
public class SysNoticeToUserServiceImpl extends ServiceImpl<SysNoticeToUserMapper, SysNoticeToUser> implements SysNoticeToUserService {

    /**
     * 全查询
     *
     * @param sysNoticeToUser 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysNoticeToUser sysNoticeToUser) {
        return R.ok().setData(baseMapper.queryAll(sysNoticeToUser));
    }

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysNoticeToUser sysNoticeToUser) {
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
    public R update(SysNoticeToUser sysNoticeToUser) {
        baseMapper.update(sysNoticeToUser);
        return R.ok().setData(this.baseMapper.selectById(sysNoticeToUser.getNoticeId()));
    }

}


