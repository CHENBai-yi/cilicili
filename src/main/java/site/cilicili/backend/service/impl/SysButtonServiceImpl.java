package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysButton;
import site.cilicili.backend.mapper.SysButtonMapper;
import site.cilicili.backend.service.SysButtonService;
import site.cilicili.common.util.R;

/**
 * (SysButton)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:38
 */
@RequiredArgsConstructor
@Service("sysButtonService")
public class SysButtonServiceImpl extends ServiceImpl<SysButtonMapper, SysButton> implements SysButtonService {

    /**
     * 通过ID查询单条数据
     *
     * @param menuName 主键
     * @return 实例对象
     */
    @Override
    public R queryById(String menuName) {
        return R.ok().setData(baseMapper.queryById(menuName));
    }

    /**
     * 全查询
     *
     * @param sysButton 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysButton sysButton) {
        return R.ok().setData(baseMapper.queryAll(sysButton));
    }

    /**
     * 新增数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysButton sysButton) {
        baseMapper.insert(sysButton);
        return R.ok().setData(sysButton);
    }

    /**
     * 修改数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysButton sysButton) {
        baseMapper.update(sysButton);
        return R.ok().setData(this.queryById(sysButton.getMenuName()));
    }

    /**
     * 通过主键删除数据
     *
     * @param menuName 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(String menuName) {
        boolean del = baseMapper.deleteById(menuName) > 0;
        return R.ok().setData(del);
    }

}


