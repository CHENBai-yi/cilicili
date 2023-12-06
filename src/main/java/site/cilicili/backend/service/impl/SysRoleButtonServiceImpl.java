package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysRoleButton;
import site.cilicili.backend.mapper.SysRoleButtonMapper;
import site.cilicili.backend.service.SysRoleButtonService;
import site.cilicili.common.util.R;

/**
 * (SysRoleButton)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
@RequiredArgsConstructor
@Service("sysRoleButtonService")
public class SysRoleButtonServiceImpl extends ServiceImpl<SysRoleButtonMapper, SysRoleButton> implements SysRoleButtonService {

    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    @Override
    public R queryById(String sysRoleRoleCode) {
        return R.ok().setData(baseMapper.queryById(sysRoleRoleCode));
    }

    /**
     * 全查询
     *
     * @param sysRoleButton 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysRoleButton sysRoleButton) {
        return R.ok().setData(baseMapper.queryAll(sysRoleButton));
    }

    /**
     * 新增数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleButton sysRoleButton) {
        baseMapper.insert(sysRoleButton);
        return R.ok().setData(sysRoleButton);
    }

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleButton sysRoleButton) {
        baseMapper.update(sysRoleButton);
        return R.ok().setData(this.queryById(sysRoleButton.getSysRoleRoleCode()));
    }

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(String sysRoleRoleCode) {
        boolean del = baseMapper.deleteById(sysRoleRoleCode) > 0;
        return R.ok().setData(del);
    }

}


