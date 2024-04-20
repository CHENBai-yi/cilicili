package site.cilicili.frontend.comments.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListResponse;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsUserInfoEntity;
import site.cilicili.frontend.comments.mapper.VideoCommentsUserInfoMapper;
import site.cilicili.frontend.comments.service.VideoCommentsService;
import site.cilicili.frontend.comments.service.VideoCommentsUserInfoService;

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
public class VideoCommentsUserInfoServiceImpl extends ServiceImpl<VideoCommentsUserInfoMapper, VideoCommentsUserInfoEntity> implements VideoCommentsUserInfoService {

    private final VideoCommentsService videoCommentsService;

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

    @Transactional(readOnly = true)
    @Override
    public R commentUserInfo(final AuthUserDetails authUserDetails, final QueryCommentListRequest queryCommentListRequest) {
        final Integer uid = Optional.ofNullable(queryCommentListRequest.id()).orElse(Math.toIntExact(authUserDetails.getId()));
        return Optional.ofNullable(baseMapper.queryByUid(uid))
                .map(data -> {
                    data.setId(data.getUid());
                    return R.yes("Success.").setData(data);
                })
                .orElse(R.no("Fail."));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R commentUserAdd(final AuthUserDetails authUserDetails, final QueryCommentListResponse.Records records, final Long courseId) {
        return Optional.ofNullable(authUserDetails)
                .map(authUserDetails1 -> baseMapper.queryByUid(Math.toIntExact(authUserDetails1.getId())))
                .map(videoCommentsUserInfoEntity -> BeanUtil.copyProperties(videoCommentsUserInfoEntity, VideoCommentsEntity.class, "id", "createdAt", "createdBy", "updatedAt", "updatedBy", "deletedAt"))
                .map(videoCommentsEntity -> {
                    videoCommentsEntity.setContent(records.getContent());
                    videoCommentsEntity.setCourseId(Math.toIntExact(courseId));
                    videoCommentsEntity.setParentId(records.getParentId());
                    videoCommentsEntity.setCreateTime(DateUtil.parse(records.getCreateTime()));
                    videoCommentsEntity.setContentImg(records.getContentImg());
                    videoCommentsEntity.setHomeLink("/" + videoCommentsEntity.getUid());
                    videoCommentsEntity.setLikes(records.getLikes());
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
                                .map(rr -> R.yes("Success."))).orElseGet(() -> Optional.of(baseMapper.like(authUserDetails.getId(), commentId))
                                .filter(integer -> integer > 0)
                                .map(rr -> R.yes("Success."))
                                .orElse(null))
                )
                .orElse(R.no("Fail."));

    }


}


