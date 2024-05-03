package site.cilicili.backend.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;
import site.cilicili.common.util.R;

import java.util.List;

/**
 * (SysNoticeToUser)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:22
 */
public interface SysNoticeToUserService extends IService<SysNoticeToUserEntity> {

    /**
     * 全查询
     *
     * @param sysNoticeToUser 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 新增数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    R insert(final SysNoticeToUserEntity sysNoticeToUser);

    /**
     * 修改数据
     *
     * @param sysNoticeToUser 实例对象
     * @return 实例对象
     */
    R update(final SysNoticeToUserEntity sysNoticeToUser);

    boolean insertOrUpdateBatch(List<SysNoticeToUserEntity> toList);

    SysNoticeToUserEntity queryById(String noticeId);

    SysNoticeToUserEntity queryNoticeByUser(String noticeId, String getusername);

    boolean updateNoticeByUser(SysNoticeToUserEntity sysNoticeToUserEntity);
}
