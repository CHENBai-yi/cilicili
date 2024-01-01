package site.cilicili.backend.dept.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.dept.domain.pojo.SysDeptUserEntity;
import site.cilicili.backend.dept.mapper.SysDeptUserMapper;
import site.cilicili.backend.dept.service.SysDeptUserService;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysDeptUser) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:52
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysDeptUserService")
public class SysDeptUserServiceImpl extends ServiceImpl<SysDeptUserMapper, SysDeptUserEntity> implements SysDeptUserService {

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
    public R queryAll(SysDeptUserEntity sysDeptUser) {
        return R.ok().setData(baseMapper.queryAll(sysDeptUser));
    }

    /**
     * 新增数据
     *
     * @param sysDeptUser 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDeptUserEntity sysDeptUser) {
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
    public R update(SysDeptUserEntity sysDeptUser) {
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

    @Override
    public boolean insertDeptUserList(final List<SysDeptUserEntity> toList) {
        return baseMapper.insertDeptUserList(toList) > 0;
    }

    @Override
    public boolean updateDeptUserList(final List<SysDeptUserEntity> toList) {
        return baseMapper.updateDeptUserList(toList) > 0;
    }

}


