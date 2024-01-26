package site.cilicili.backend.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryRequest;
import site.cilicili.backend.notice.domain.dto.QueryNoticeReadRequest;
import site.cilicili.backend.notice.domain.dto.SendNoticeRequest;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;
import site.cilicili.common.util.R;

/**
 * (SysNotice)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:21
 */
public interface SysNoticeService extends IService<SysNoticeEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Long id);

    /**
     * 全查询
     *
     * @param sysNotice 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysNoticeEntity sysNotice);

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    R insert(final SysNoticeEntity sysNotice);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    R update(final SysNoticeEntity sysNotice);

    /**
     * 通过主键删除数据
     *
     * @param authUserDetails
     * @param sysNotice       主键
     * @return 是否成功
     */
    R deleteById(final AuthUserDetails authUserDetails, final SysNoticeEntity sysNotice);

    R getNoticeList(NoticeListQueryRequest noticeListQueryRequest);

    R sendNotice(AuthUserDetails authUserDetails, SendNoticeRequest noticeListQueryRequest);

    R queryNoticeReadById(final AuthUserDetails authUserDetails, QueryNoticeReadRequest queryNoticeReadRequest);
}
