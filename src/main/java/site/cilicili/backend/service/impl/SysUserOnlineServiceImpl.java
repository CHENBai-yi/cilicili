package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysUserOnline;
import site.cilicili.backend.mapper.SysUserOnlineMapper;
import site.cilicili.backend.service.SysUserOnlineService;
import site.cilicili.common.util.R;

/**
 * (SysUserOnline)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@RequiredArgsConstructor
@Service("sysUserOnlineService")
public class SysUserOnlineServiceImpl extends ServiceImpl<SysUserOnlineMapper, SysUserOnline> implements SysUserOnlineService {

    /**
     * 全查询
     *
     * @param sysUserOnline 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysUserOnline sysUserOnline) {
        return R.ok().setData(baseMapper.queryAll(sysUserOnline));
    }

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUserOnline sysUserOnline) {
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
    public R update(SysUserOnline sysUserOnline) {
        baseMapper.update(sysUserOnline);
        return R.ok().setData(this.queryAll(sysUserOnline));
    }

}


