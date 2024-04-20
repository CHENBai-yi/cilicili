package site.cilicili.frontend.comments.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListResponse;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsUserInfoEntity;

/**
 * (VideoCommentsUserInfo)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-04-19 00:00:07
 */
public interface VideoCommentsUserInfoService extends IService<VideoCommentsUserInfoEntity> {

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
     * @param videoCommentsUserInfo 筛选条件
     * @return 查询结果
     */
    R queryAll(final VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 新增数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 实例对象
     */
    R insert(final VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 修改数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 实例对象
     */
    R update(final VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R commentUserInfo(AuthUserDetails authUserDetails, final QueryCommentListRequest queryCommentListRequest);

    R commentUserAdd(AuthUserDetails authUserDetails, QueryCommentListResponse.Records records, final Long courseId);

    R favorite(AuthUserDetails authUserDetails, Long courseId);
}
       

