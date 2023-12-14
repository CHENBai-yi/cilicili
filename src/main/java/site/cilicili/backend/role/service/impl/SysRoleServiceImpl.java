package site.cilicili.backend.role.service.impl;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.backend.role.domain.dto.RoleListQueryParam;
import site.cilicili.backend.role.domain.dto.SysRoleDto;
import site.cilicili.backend.role.domain.pojo.SysRoleEntity;
import site.cilicili.backend.role.mapper.SysRoleMapper;
import site.cilicili.backend.role.service.SysRoleService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysRole) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 03:55:21
 */
@RequiredArgsConstructor
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

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
     * @param sysRole 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysRoleEntity sysRole) {
        return R.ok().setData(baseMapper.queryAll(sysRole));
    }

    /**
     * 新增数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysRoleEntity sysRole) {
        if (ObjUtil.isNotNull(baseMapper.selectOne(new QueryWrapper<SysRoleEntity>().eq("role_code", sysRole.getRoleCode())))) {
            throw new AppException(Error.DUPLICATED_USER);
        } else {
            if (baseMapper.insert(sysRole) > 0) {
                return R.yes("添加成功.");
            }
            throw new AppException(Error.USER_NOT_FOUND);
        }
    }

    /**
     * 修改数据
     *
     * @param sysRole 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysRoleEntity sysRole) {
        baseMapper.update(sysRole);
        return R.ok().setData(this.queryById(sysRole.getId()));
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

    @Override
    public R queryRoleListByParam(final RoleListQueryParam queryParam) {
        final SysRoleDto.SysRoleDtoBuilder page = SysRoleDto.builder().page(queryParam.getPage()).pageSize(queryParam.getPageSize());
        queryParam.setPage((queryParam.getPage() * queryParam.getPageSize()) - queryParam.getPageSize());
        return Optional.ofNullable(baseMapper.queryRoleListByParam(queryParam)).map(records -> R.yes("Success").setData(page.total(records.size()).records(records).build())).orElse(R.yes("Success").setData(page.build()));
    }

}


