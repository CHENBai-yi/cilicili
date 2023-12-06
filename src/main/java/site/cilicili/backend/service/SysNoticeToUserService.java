package site.cilicili.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.domain.pojo.SysNoticeToUser;
import site.cilicili.common.util.R;

/**
 * (SysNoticeToUser)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:44
 */
public interface SysNoticeToUserService extends IService<SysNoticeToUser> {

    /**
     * 全查询
     *
     * @param sysNoticeToUser 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysNoticeToUser sysNoticeToUser);

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    R insert(final SysNoticeToUser sysNoticeToUser);

    /**
     * 修改数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    R update(final SysNoticeToUser sysNoticeToUser);


}
       

