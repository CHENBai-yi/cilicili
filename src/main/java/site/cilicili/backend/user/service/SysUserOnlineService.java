package site.cilicili.backend.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.user.domain.dto.KickOnlineUserRequest;
import site.cilicili.backend.user.domain.dto.QueryUserOnlineListRequest;
import site.cilicili.backend.user.domain.pojo.SysUserOnlineEntity;
import site.cilicili.common.util.R;

/**
 * (SysUserOnline)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-18 20:39:31
 */
public interface SysUserOnlineService extends IService<SysUserOnlineEntity> {

    /**
     * 全查询
     *
     * @param sysUserOnline 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysUserOnlineEntity sysUserOnline);

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    R insert(final SysUserOnlineEntity sysUserOnline);

    /**
     * 修改数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    R update(final SysUserOnlineEntity sysUserOnline);

    R kickOnlineUser(KickOnlineUserRequest kickOnlineUserRequest);

    R getUserOnlineList(QueryUserOnlineListRequest queryUserOnlineListRequest);

    boolean insertOrUpdate(String username, String encode);

    SysUserOnlineEntity queryById(String id, String token);

    boolean dropOnline();

    boolean removeByNameOrToken(String username, String token);
}
       

