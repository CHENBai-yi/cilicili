package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysRoleApi;
import site.cilicili.common.util.R;

/**
 * (SysRoleApi)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:45
 */
public interface SysRoleApiService extends IService<SysRoleApi> {

    /**
     * 全查询
     *
     * @param sysRoleApi 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysRoleApi sysRoleApi);

    /**
     * 新增数据
     *
     * @param sysRoleApi 实例对象
     * @return 实例对象
     */
    R insert(final SysRoleApi sysRoleApi);

    /**
     * 修改数据
     *
     * @param sysRoleApi 实例对象
     * @return 实例对象
     */
    R update(final SysRoleApi sysRoleApi);


}
       

