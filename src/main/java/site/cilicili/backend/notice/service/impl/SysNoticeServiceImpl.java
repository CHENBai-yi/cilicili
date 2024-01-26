package site.cilicili.backend.notice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryParamResponse;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryRequest;
import site.cilicili.backend.notice.domain.dto.QueryNoticeReadRequest;
import site.cilicili.backend.notice.domain.dto.SendNoticeRequest;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;
import site.cilicili.backend.notice.domain.pojo.SysNoticeToUserEntity;
import site.cilicili.backend.notice.mapper.SysNoticeMapper;
import site.cilicili.backend.notice.service.SysNoticeService;
import site.cilicili.backend.notice.service.SysNoticeToUserService;
import site.cilicili.backend.user.service.SysUserService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.common.websocket.CiliMessage;
import site.cilicili.common.websocket.WebSocketEndpoint;

import java.util.List;
import java.util.Optional;

/**
 * (SysNotice) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:21
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysNoticeService")
public class SysNoticeServiceImpl extends ServiceImpl<SysNoticeMapper, SysNoticeEntity> implements SysNoticeService {

    private final SysNoticeToUserService sysNoticeToUserService;
    private final SysUserService sysUserService;
    private final WebSocketEndpoint webSocketEndpoint;

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
     * @param sysNotice 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysNoticeEntity sysNotice) {
        return R.ok().setData(baseMapper.queryAll(sysNotice));
    }

    /**
     * 新增消息
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R insert(SysNoticeEntity sysNotice) {
        return Optional.ofNullable(sysNotice.getNoticeToUser())
                .filter(users -> !users.isEmpty())
                .map(users -> getR(sysNotice, users))
                .orElseGet(() -> Optional.ofNullable(sysUserService.getUsernameList())
                        .filter(usernameList -> !usernameList.isEmpty())
                        .map(usernameList -> getR(sysNotice, usernameList))
                        .orElseThrow(() -> new AppException("消息新增失败.")));
    }

    private R getR(final SysNoticeEntity sysNotice, final List<String> users) {
        sysNotice.setNoticeId(IdUtil.fastUUID());
        return Optional.of(users.stream()
                        .map(name -> {
                            final SysNoticeToUserEntity sysNoticeToUserEntity =
                                    BeanUtil.toBean(sysNotice, SysNoticeToUserEntity.class);
                            sysNoticeToUserEntity.setToUser(name);
                            return sysNoticeToUserEntity;
                        })
                        .toList())
                .filter(sysNoticeToUserEntities -> sysNoticeToUserService.insertOrUpdateBatch(sysNoticeToUserEntities)
                        && baseMapper.insert(sysNotice) > 0)
                .map(r -> R.yes("添加成功."))
                .orElseThrow(() -> new AppException("消息新增失败."));
    }

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysNoticeEntity sysNotice) {
        baseMapper.update(sysNotice);
        return R.ok().setData(this.queryById(sysNotice.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param authUserDetails
     * @param sysNotice       主键
     * @return 是否成功
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R deleteById(final AuthUserDetails authUserDetails, SysNoticeEntity sysNotice) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> Optional.of(baseMapper.deleteById(sysNotice))
                        .filter(integer -> integer > 0)
                        .map(integer -> R.yes(String.format("%1$s删除成功.", auth.getusername())))
                        .orElse(R.no("删除失败.")))
                .orElseThrow(() -> new AppException(Error.LOGIN_INFO_INVALID));
    }

    @Override
    @Transactional(readOnly = true)
    public R getNoticeList(final NoticeListQueryRequest noticeListQueryRequest) {
        return Optional.ofNullable(baseMapper.selectNoticeListByParam(noticeListQueryRequest))
                .map(records -> R.yes("Success.")
                        .setData(NoticeListQueryParamResponse.builder()
                                .records(records)
                                .total(records.size())
                                .page(noticeListQueryRequest.page())
                                .pageSize(noticeListQueryRequest.pageSize())
                                .build()))
                .orElse(R.no("Fail."));
    }

    // todo 完成消息发送
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R sendNotice(final AuthUserDetails authUserDetails, final SendNoticeRequest noticeListQueryRequest) {
        return Optional.ofNullable(authUserDetails)
                .filter(auth -> webSocketEndpoint.sendMoreMessage(
                        noticeListQueryRequest.getNoticeToUser().stream()
                                .map(SysNoticeToUserEntity::getToUser)
                                .toList(),
                        CiliMessage.builder()
                                .text(noticeListQueryRequest.getNoticeContent())
                                .messageType(noticeListQueryRequest.getNoticeType())
                                .build()))
                .filter(auth -> {
                    noticeListQueryRequest.setNoticeSent("yesNo_yes");
                    return this.updateById(BeanUtil.toBean(noticeListQueryRequest, SysNoticeEntity.class));
                })
                .map(auth -> R.yes(String.format("%1$s发送成功.", auth.getusername())))
                .orElseThrow(() -> new AppException("消息发送失败！"));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public R queryNoticeReadById(
            final AuthUserDetails authUserDetails, final QueryNoticeReadRequest queryNoticeReadRequest) {
        return Optional.of(authUserDetails)
                .map(auth -> baseMapper.queryById(queryNoticeReadRequest.id()))
                .filter(sysNoticeEntity -> {
                    final SysNoticeToUserEntity sysNoticeToUserEntity = sysNoticeToUserService.queryNoticeByUser(
                            sysNoticeEntity.getNoticeId(), authUserDetails.getusername());
                    sysNoticeToUserEntity.setUserRead("yesNo_yes");
                    return sysNoticeToUserService.updateNoticeByUser(sysNoticeToUserEntity);
                })
                .map(r -> R.yes("查询成功.").setRecords(r))
                .orElseThrow(() -> new AppException("消息查看失败!"));
    }
}
