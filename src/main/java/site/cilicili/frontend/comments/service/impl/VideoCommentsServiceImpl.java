package site.cilicili.frontend.comments.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListResponse;
import site.cilicili.frontend.comments.domain.dto.VideoCommentsDto;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;
import site.cilicili.frontend.comments.mapper.VideoCommentsMapper;
import site.cilicili.frontend.comments.service.VideoCommentsService;

import java.util.List;
import java.util.Optional;

/**
 * (VideoComments) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:14
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("videoCommentsService")
public class VideoCommentsServiceImpl extends ServiceImpl<VideoCommentsMapper, VideoCommentsEntity> implements VideoCommentsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param videoComments 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(VideoCommentsEntity videoComments) {
        return R.ok().setData(baseMapper.queryAll(videoComments));
    }

    /**
     * 新增数据
     *
     * @param videoComments 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(VideoCommentsEntity videoComments) {
        baseMapper.insert(videoComments);
        return R.ok().setData(videoComments);
    }

    /**
     * 修改数据
     *
     * @param videoComments 实例对象
     * @return 实例对象
     */
    @Override
    public R update(VideoCommentsEntity videoComments) {
        baseMapper.update(videoComments);
        return R.ok().setData(this.queryById(Math.toIntExact(videoComments.getId())));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Transactional(readOnly = true)
    @Override
    public R commentsList(final QueryCommentListRequest queryCommentListRequest) {
        return Optional.ofNullable(baseMapper.queryCommentsListByParam(queryCommentListRequest))
                .map(videoCommentsEntities -> {
                    final List<QueryCommentListResponse.Records> collect = videoCommentsEntities.stream().map(item -> QueryCommentListResponse.Records.builder()
                            .createTime(DateUtil.formatDate(item.getCreateTime()))
                            .contentImg(item.getContentImg())
                            .likes(item.getLikes())
                            .content(item.getContent())
                            .address(item.getAddress())
                            .uid(String.valueOf(item.getUid()))
                            .parentId(item.getParentId())
                            .id(String.valueOf(item.getId()))
                            .user(QueryCommentListResponse.User.builder()
                                    .level(item.getLevel())
                                    .avatar(item.getAvatar())
                                    .username(item.getUsername())
                                    .homeLink(item.getHomeLink())
                                    .build())
                            .build()).toList();
                    final VideoCommentsEntity comments = new VideoCommentsEntity();
                    comments.setCourseId(queryCommentListRequest.id());
                    return R.yes("Success.").setData(QueryCommentListResponse.builder()
                            .records(collect)
                            .total(Math.toIntExact(baseMapper.count(comments)))
                            .pageSize(queryCommentListRequest.pageSize())
                            .page(queryCommentListRequest.page())
                            .build());
                })
                .orElse(R.no("Fail."));
    }

    @Transactional(readOnly = true)
    @Override
    public R commentsList2(final QueryCommentListRequest queryCommentListRequest) {
        final VideoCommentsEntity comments = new VideoCommentsEntity();
        comments.setCourseId(queryCommentListRequest.id());
        return Optional.ofNullable(baseMapper.queryCommentsListByParam2(queryCommentListRequest))
                .map(collect -> {
                    collect.toString();
                    return R.yes("Success.").setData(QueryCommentListResponse.builder()
                            .records(collect)
                            .total(Math.toIntExact(baseMapper.count(comments)))
                            .pageSize(queryCommentListRequest.pageSize())
                            .page(queryCommentListRequest.page())
                            .build());
                }).orElse(R.no("Fail."));
    }

    @Transactional(readOnly = true)
    @Override
    public R userInfo(final AuthUserDetails authUserDetails) {

        return Optional.ofNullable(baseMapper.queryByUid(Math.toIntExact(authUserDetails.getId())))
                .map(r -> R.yes("Success.").setData(VideoCommentsDto.builder().id(r.getUid()).username(String.format("%1$s%2$s", r.getUsername(), r.getUid())).avatar(r.getAvatar()).level(r.getLevel())
                        .likes(r.getLikes())
                        .attention(RandomUtil.randomInt(0, 100))
                        .follower(RandomUtil.randomInt(0, 10000))
                        .build()))
                .orElse(R.no("Fail."));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R delComments(final AuthUserDetails authUserDetails, final Long commentId) {
        return Optional.ofNullable(Optional.ofNullable(baseMapper.queryById(Math.toIntExact(commentId)))
                .map(VideoCommentsEntity::getParentId)
                .filter(id -> removeById(commentId))
                .map(r -> R.yes("删除成功."))
                .orElseGet(() -> Optional.ofNullable(baseMapper.delComments(authUserDetails.getId(), commentId))
                        .filter(f -> f > 0)
                        .map(r -> R.yes("删除成功."))
                        .orElse(null))).orElse(R.no("Fail."));
    }

}


