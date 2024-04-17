package site.cilicili.frontend.comments.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListRequest;
import site.cilicili.frontend.comments.domain.dto.QueryCommentListResponse;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-16 22:09:12
 */

/**
 * (VideoComments)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-04-16 22:09:12
 */
@Mapper
public interface VideoCommentsMapper extends BaseMapper<VideoCommentsEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCommentsEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param videoComments 查询条件
     * @return 对象列表
     */
    List<VideoCommentsEntity> queryAll(VideoCommentsEntity videoComments);

    /**
     * 统计总行数
     *
     * @param videoComments 查询条件
     * @return 总行数
     */
    long count(VideoCommentsEntity videoComments);

    @Override
    /**
     * 新增数据
     *
     * @param videoComments 实例对象
     * @return 影响行数
     */
    int insert(VideoCommentsEntity videoComments);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCommentsEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoCommentsEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCommentsEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoCommentsEntity> entities);

    /**
     * 修改数据
     *
     * @param videoComments 实例对象
     * @return 影响行数
     */
    int update(VideoCommentsEntity videoComments);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<VideoCommentsEntity> queryCommentsListByParam(QueryCommentListRequest queryCommentListRequest);

    List<QueryCommentListResponse.Records> queryCommentsListByParam2(QueryCommentListRequest queryCommentListRequest);
}


