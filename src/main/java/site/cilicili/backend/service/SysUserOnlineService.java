package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysUserOnline;
import site.cilicili.common.util.R;

/**
 * (SysUserOnline)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
public interface SysUserOnlineService extends IService<SysUserOnline> {

    /**
     * 全查询
     *
     * @param sysUserOnline 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysUserOnline sysUserOnline);

    /**
     * 新增数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    R insert(final SysUserOnline sysUserOnline);

    /**
     * 修改数据
     *
     * @param sysUserOnline 实例对象
     * @return 实例对象
     */
    R update(final SysUserOnline sysUserOnline);


}
       

