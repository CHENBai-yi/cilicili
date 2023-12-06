package site.cilicili.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.domain.pojo.SysDeptUser;
import site.cilicili.backend.mapper.SysDeptUserMapper;
import site.cilicili.backend.service.SysDeptUserService;
import site.cilicili.common.util.R;

/**
 * (SysDeptUser)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:40
 */
@RequiredArgsConstructor
@Service("sysDeptUserService")
public class SysDeptUserServiceImpl extends ServiceImpl<SysDeptUserMapper, SysDeptUser> implements SysDeptUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param sysDeptDeptCode 主键
     * @return 实例对象
     */
    @Override
    public R queryById(String sysDeptDeptCode) {
        return R.ok().setData(baseMapper.queryById(sysDeptDeptCode));
    }

    /**
     * 全查询
     *
     * @param sysDeptUser 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysDeptUser sysDeptUser) {
        return R.ok().setData(baseMapper.queryAll(sysDeptUser));
    }

    /**
     * 新增数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDeptUser sysDeptUser) {
        baseMapper.insert(sysDeptUser);
        return R.ok().setData(sysDeptUser);
    }

    /**
     * 修改数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysDeptUser sysDeptUser) {
        baseMapper.update(sysDeptUser);
        return R.ok().setData(this.queryById(sysDeptUser.getSysDeptDeptCode()));
    }

    /**
     * 通过主键删除数据
     *
     * @param sysDeptDeptCode 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(String sysDeptDeptCode) {
        boolean del = baseMapper.deleteById(sysDeptDeptCode) > 0;
        return R.ok().setData(del);
    }

}


