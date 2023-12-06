package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysGenPluginList;
import site.cilicili.backend.mapper.SysGenPluginListMapper;
import site.cilicili.backend.service.SysGenPluginListService;
import site.cilicili.common.util.R;

/**
 * (SysGenPluginList)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:41
 */
@RequiredArgsConstructor
@Service("sysGenPluginListService")
public class SysGenPluginListServiceImpl extends ServiceImpl<SysGenPluginListMapper, SysGenPluginList> implements SysGenPluginListService {

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
     * @param sysGenPluginList 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysGenPluginList sysGenPluginList) {
        return R.ok().setData(baseMapper.queryAll(sysGenPluginList));
    }

    /**
     * 新增数据
     *
     * @param sysGenPluginList 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysGenPluginList sysGenPluginList) {
        baseMapper.insert(sysGenPluginList);
        return R.ok().setData(sysGenPluginList);
    }

    /**
     * 修改数据
     *
     * @param sysGenPluginList 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysGenPluginList sysGenPluginList) {
        baseMapper.update(sysGenPluginList);
        return R.ok().setData(this.queryById(sysGenPluginList.getId()));
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


