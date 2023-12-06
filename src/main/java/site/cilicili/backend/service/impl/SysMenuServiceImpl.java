package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysMenu;
import site.cilicili.backend.mapper.SysMenuMapper;
import site.cilicili.backend.service.SysMenuService;
import site.cilicili.common.util.R;

/**
 * (SysMenu)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:43
 */
@RequiredArgsConstructor
@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

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
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysMenu sysMenu) {
        return R.ok().setData(baseMapper.queryAll(sysMenu));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysMenu sysMenu) {
        baseMapper.insert(sysMenu);
        return R.ok().setData(sysMenu);
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysMenu sysMenu) {
        baseMapper.update(sysMenu);
        return R.ok().setData(this.queryById(sysMenu.getId()));
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


