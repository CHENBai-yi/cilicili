package site.cilicili.backend.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.api.domain.dto.GetRoleApiListRequest;
import site.cilicili.backend.api.domain.pojo.SysRoleApiEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysRoleApi)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:22:10
 */
public interface SysRoleApiService extends IService<SysRoleApiEntity> {

    R getRoleApiList(GetRoleApiListRequest apiListRequest);

    boolean insertOrUpdateBatch(List<SysRoleApiEntity> roleApi);
}
