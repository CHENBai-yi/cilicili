package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysUser;
import site.cilicili.backend.mapper.SysUserMapper;
import site.cilicili.backend.service.SysUserService;
import site.cilicili.common.util.R;

/**
 * (SysUser)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@RequiredArgsConstructor
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

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
     * @param sysUser 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysUser sysUser) {
        return R.ok().setData(baseMapper.queryAll(sysUser));
    }

    /**
     * 新增数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysUser sysUser) {
        baseMapper.insert(sysUser);
        return R.ok().setData(sysUser);
    }

    /**
     * 修改数据
     *
     * @param sysUser 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysUser sysUser) {
        baseMapper.update(sysUser);
        return R.ok().setData(this.queryById(sysUser.getId()));
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

}


