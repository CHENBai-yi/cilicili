package site.cilicili.backend.button.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.backend.button.mapper.SysButtonMapper;
import site.cilicili.backend.button.service.SysButtonService;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysButton) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:46:34
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysButtonService")
public class SysButtonServiceImpl extends ServiceImpl<SysButtonMapper, SysButtonEntity> implements SysButtonService {

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
    public R queryAll(SysButtonEntity sysButton) {
        return R.ok().setData(baseMapper.queryAll(sysButton));
    }

    /**
     * 新增数据
     *
     * @param sysButton 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysButtonEntity sysButton) {
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
    public R update(SysButtonEntity sysButton) {
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

    @Override
    @Transactional(readOnly = true)
    public boolean insertOrUpdate(final List<SysButtonEntity> button) {
        return baseMapper.insertOrUpdate(button) > 0;
    }
}
