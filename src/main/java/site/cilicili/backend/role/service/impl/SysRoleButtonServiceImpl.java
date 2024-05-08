package site.cilicili.backend.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.role.domain.dto.RoleButtonMenuRequest;
import site.cilicili.backend.role.domain.pojo.SysRoleButtonEntity;
import site.cilicili.backend.role.mapper.SysRoleButtonMapper;
import site.cilicili.backend.role.service.SysRoleButtonService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.Optional;

/**
 * (SysRoleButton) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:56
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysRoleButtonService")
public class SysRoleButtonServiceImpl extends ServiceImpl<SysRoleButtonMapper, SysRoleButtonEntity>
        implements SysRoleButtonService {

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
    public R queryAll(SysRoleButtonEntity sysRoleButton) {
        return R.ok().setData(baseMapper.queryAll(sysRoleButton));
    }

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleButtonEntity sysRoleButton) {
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

    @Override
    @Transactional(readOnly = true)
    public R getRoleButtonList(final RoleButtonMenuRequest roleCode) {
        return Optional.ofNullable(baseMapper.selectList(
                        new QueryWrapper<SysRoleButtonEntity>().eq("sys_role_role_code", roleCode.roleCode())))
                .map(sysRoleButtonEntities -> R.yes("Success").setData("records", sysRoleButtonEntities))
                .orElse(R.no("Fail"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public boolean insertOrUpdateBatch(final List<SysRoleButtonEntity> roleButton) {
        return baseMapper.insertOrUpdateBatch(roleButton) > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public R getRoleButtonStrList(final AuthUserDetails roleCode) {
        return Optional.ofNullable(roleCode).map(authUserDetails ->
                Optional.ofNullable(authUserDetails.getRoleCode())
                        .map(r -> baseMapper.findButtons(r))
                        .map(data -> R.yes("Success.").setRecords(data))
                        .orElse(R.no("Fail."))
        ).orElseThrow(() -> new AppException(Error.TOKEN_INVALIED));
    }

}
