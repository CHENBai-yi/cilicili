package site.cilicili.backend.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.api.domain.dto.GetRoleApiListRequest;
import site.cilicili.backend.api.domain.pojo.SysRoleApiEntity;
import site.cilicili.backend.api.mapper.SysRoleApiMapper;
import site.cilicili.backend.api.service.SysRoleApiService;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysRoleApi) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:22:10
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysRoleApiService")
public class SysRoleApiServiceImpl extends ServiceImpl<SysRoleApiMapper, SysRoleApiEntity>
        implements SysRoleApiService {

    @Override
    @Transactional(readOnly = true)
    public R getRoleApiList(final GetRoleApiListRequest roleCode) {
        return Optional.ofNullable(baseMapper.selectList(
                        new QueryWrapper<SysRoleApiEntity>().eq("role_code", roleCode.roleCode())))
                .map(sysRoleApiEntities -> R.yes("Success").setData("records", sysRoleApiEntities))
                .orElse(R.no("Fail"));
    }
}
