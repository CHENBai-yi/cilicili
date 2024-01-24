package site.cilicili.backend.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.role.domain.dto.RoleButtonMenuRequest;
import site.cilicili.backend.role.domain.pojo.SysRoleButtonEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysRoleButton)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-29 21:20:56
 */
public interface SysRoleButtonService extends IService<SysRoleButtonEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param sysRoleRoleCode 主键
     * @return 实例对象
     */
    R queryById(final String sysRoleRoleCode);

    /**
     * 全查询
     *
     * @param sysRoleButton 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysRoleButtonEntity sysRoleButton);

    /**
     * 修改数据
     *
     * @param sysRoleButton 实例对象
     * @return 实例对象
     */
    R update(final SysRoleButtonEntity sysRoleButton);

    /**
     * 通过主键删除数据
     *
     * @param sysRoleRoleCode 主键
     * @return 是否成功
     */
    R deleteById(final String sysRoleRoleCode);

    R getRoleButtonList(RoleButtonMenuRequest roleCode);

    boolean insertOrUpdateBatch(List<SysRoleButtonEntity> roleButton);
}
