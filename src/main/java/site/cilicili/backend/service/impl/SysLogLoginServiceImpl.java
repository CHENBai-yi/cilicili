package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysLogLogin;
import site.cilicili.backend.mapper.SysLogLoginMapper;
import site.cilicili.backend.service.SysLogLoginService;
import site.cilicili.common.util.R;

/**
 * (SysLogLogin)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:41
 */
@RequiredArgsConstructor
@Service("sysLogLoginService")
public class SysLogLoginServiceImpl extends ServiceImpl<SysLogLoginMapper, SysLogLogin> implements SysLogLoginService {

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
    public R queryAll(SysLogLogin sysLogLogin) {
        return R.ok().setData(baseMapper.queryAll(sysLogLogin));
    }

    /**
     * 新增数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysLogLogin sysLogLogin) {
        baseMapper.insert(sysLogLogin);
        return R.ok().setData(sysLogLogin);
    }

    /**
     * 修改数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysLogLogin sysLogLogin) {
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

}


