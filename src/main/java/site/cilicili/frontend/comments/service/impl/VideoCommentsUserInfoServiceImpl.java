package site.cilicili.frontend.comments.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.AddressUtils;
import site.cilicili.common.util.IpUtil;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListResponse;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsUserInfoEntity;
import site.cilicili.frontend.comments.mapper.VideoCommentsUserInfoMapper;
import site.cilicili.frontend.comments.service.VideoCommentsService;
import site.cilicili.frontend.comments.service.VideoCommentsUserInfoService;

import java.util.Collections;
import java.util.Optional;

/**
 * (VideoCommentsUserInfo) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-19 00:00:07
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("videoCommentsUserInfoService")
public class VideoCommentsUserInfoServiceImpl
        extends ServiceImpl<VideoCommentsUserInfoMapper, VideoCommentsUserInfoEntity>
        implements VideoCommentsUserInfoService {

    private final VideoCommentsService videoCommentsService;
    private final HttpServletRequest httpServletRequest;

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
     * @param videoCommentsUserInfo 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(VideoCommentsUserInfoEntity videoCommentsUserInfo) {
        return R.ok().setData(baseMapper.queryAll(videoCommentsUserInfo));
    }

    /**
     * 新增数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(VideoCommentsUserInfoEntity videoCommentsUserInfo) {
        baseMapper.insert(videoCommentsUserInfo);
        return R.ok().setData(videoCommentsUserInfo);
    }

    /**
     * 修改数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 实例对象
     */
    @Override
    public R update(VideoCommentsUserInfoEntity videoCommentsUserInfo) {
        baseMapper.update(videoCommentsUserInfo);
        return R.ok().setData(this.queryById(videoCommentsUserInfo.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Long id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R commentUserInfo(
            final AuthUserDetails authUserDetails, final QueryCommentListRequest queryCommentListRequest) {
        final String address = AddressUtils.getRealAddressByIP(IpUtil.getRemoteIp(httpServletRequest));
        return Optional.ofNullable(queryCommentListRequest.id())
                .filter(id -> id.intValue() != authUserDetails.getId())
                .map(id -> Optional.ofNullable(baseMapper.queryByUid(id))
                        .map(data -> {
                            data.setId(data.getUid());
                            return R.yes("Success.").setData(data);
                        })
                        .orElse(R.no("Fail.")))
                .orElseGet(() -> Optional.ofNullable(authUserDetails)
                        .map(AuthUserDetails::getId)
                        .map(uid -> Optional.ofNullable(baseMapper.queryByUid(Math.toIntExact(uid)))
                                .orElseGet(() -> {
                                    final VideoCommentsUserInfoEntity userInfoEntity =
                                            new VideoCommentsUserInfoEntity();
                                    userInfoEntity.setUid(uid);
                                    userInfoEntity.setUsername(authUserDetails.getUsername());
                                    userInfoEntity.setAvatar(authUserDetails.getAvatar());
                                    userInfoEntity.setLevel(1);
                                    userInfoEntity.setFollower(0L);
                                    userInfoEntity.setLike(0L);
                                    userInfoEntity.setAttention(0L);
                                    userInfoEntity.setLikeIdsArr(Collections.emptyList());
                                    userInfoEntity.setAddress(address);
                                    if (save(userInfoEntity)) {
                                        return userInfoEntity;
                                    }
                                    return null;
                                }))
                        .map(data -> {
                            boolean f = false;
                            data.setUsername(Optional.ofNullable(authUserDetails.getNickName())
                                    .orElse(authUserDetails.getRealName()));
                            final String avatar = authUserDetails.getAvatar();
                            final String username = Optional.ofNullable(authUserDetails.getNickName())
                                    .orElse(authUserDetails.getRealName());
                            if (StrUtil.isNotEmpty(avatar) && !avatar.equals(data.getAvatar())) {
                                data.setAvatar(avatar);
                                f = true;
                            }
                            if (StrUtil.isNotEmpty(username) && !username.equals(data.getUsername())) {
                                data.setUsername(username);
                                f = true;
                            }
                            if (StrUtil.isNotEmpty(data.getAddress())
                                    && !data.getAddress().equals(address)) {
                                data.setAddress(address);
                                f = true;
                            }
                            if (f) {
                                update(data);
                            }
                            data.setId(data.getUid());
                            return R.yes("Success.").setData(data);
                        })
                        .orElse(R.no("Fail.")));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R commentUserAdd(
            final AuthUserDetails authUserDetails,
            final QueryCommentListResponse.Records records,
            final Long courseId) {
        return Optional.ofNullable(authUserDetails)
                .map(authUserDetails1 -> baseMapper.queryByUid(Math.toIntExact(authUserDetails1.getId())))
                .map(videoCommentsUserInfoEntity -> BeanUtil.copyProperties(
                        videoCommentsUserInfoEntity,
                        VideoCommentsEntity.class,
                        "id",
                        "createdAt",
                        "createdBy",
                        "updatedAt",
                        "updatedBy",
                        "deletedAt"))
                .map(videoCommentsEntity -> {
                    videoCommentsEntity.setContent(records.getContent());
                    videoCommentsEntity.setCourseId(Math.toIntExact(courseId));
                    videoCommentsEntity.setParentId(records.getParentId());
                    videoCommentsEntity.setCreateTime(DateUtil.parse(records.getCreateTime()));
                    videoCommentsEntity.setContentImg(records.getContentImg());
                    videoCommentsEntity.setHomeLink("/" + videoCommentsEntity.getUid());
                    videoCommentsEntity.setLikes(records.getLikes());
                    Optional.ofNullable(records.getUser()).ifPresent(user -> {
                        videoCommentsEntity.setAvatar(user.getAvatar());
                        videoCommentsEntity.setUsername(user.getUsername());
                    });
                    return videoCommentsEntity;
                })
                .filter(videoCommentsService::save)
                .map(videoCommentsEntity -> {
                    BeanUtil.copyProperties(videoCommentsEntity, records.getUser());
                    BeanUtil.copyProperties(videoCommentsEntity, records);
                    return R.yes("评论成功!").setData(records);
                })
                .orElseThrow(() -> new AppException(Error.USER_NOT_FOUND));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R favorite(final AuthUserDetails authUserDetails, final Long commentId) {
        return Optional.ofNullable(baseMapper.queryByUid(Math.toIntExact(authUserDetails.getId())))
                .map(VideoCommentsUserInfoEntity::getLikeIds)
                .map(likeIds -> Optional.of(likeIds)
                        .filter(likeStr -> likeStr.contains(String.valueOf(commentId)))
                        .flatMap(r -> Optional.of(baseMapper.unlike(authUserDetails.getId(), commentId))
                                .filter(integer -> integer > 0)
                                .map(rr -> R.yes("Success.")))
                        .orElseGet(() -> Optional.of(baseMapper.like(authUserDetails.getId(), commentId))
                                .filter(integer -> integer > 0)
                                .map(rr -> R.yes("Success."))
                                .orElse(null)))
                .orElse(R.no("Fail."));
    }
}
