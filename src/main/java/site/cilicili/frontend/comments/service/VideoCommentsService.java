package site.cilicili.frontend.comments.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;

/**
 * (VideoComments)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:13
 */
public interface VideoCommentsService extends IService<VideoCommentsEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Integer id);

    /**
     * 全查询
     *
     * @param videoComments 筛选条件
     * @return 查询结果
     */
    R queryAll(final VideoCommentsEntity videoComments);

    /**
     * 新增数据
     *
     * @param videoComments 实例对象
     * @return 实例对象
     */
    R insert(final VideoCommentsEntity videoComments);

    /**
     * 修改数据
     *
     * @param videoComments 实例对象
     * @return 实例对象
     */
    R update(final VideoCommentsEntity videoComments);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Integer id);

    R commentsList(QueryCommentListRequest queryCommentListRequest);

    R commentsList2(QueryCommentListRequest queryCommentListRequest);

    R userInfo(AuthUserDetails authUserDetails);

    R delComments(AuthUserDetails authUserDetails, Long commentId);
}
