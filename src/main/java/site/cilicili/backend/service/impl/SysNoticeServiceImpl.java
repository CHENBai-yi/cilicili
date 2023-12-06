package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysNotice;
import site.cilicili.backend.mapper.SysNoticeMapper;
import site.cilicili.backend.service.SysNoticeService;
import site.cilicili.common.util.R;

/**
 * (SysNotice)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
@RequiredArgsConstructor
@Service("sysNoticeService")
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNotice> implements SysNoticeService {

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
     * @param sysNotice 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysNotice sysNotice) {
        return R.ok().setData(baseMapper.queryAll(sysNotice));
    }

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysNotice sysNotice) {
        baseMapper.insert(sysNotice);
        return R.ok().setData(sysNotice);
    }

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysNotice sysNotice) {
        baseMapper.update(sysNotice);
        return R.ok().setData(this.queryById(sysNotice.getId()));
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


