package site.cilicili.frontend.comments.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.comments.domain.pojo.VideoCommentsUserInfoEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-19 00:00:06
 */

/**
 * (VideoCommentsUserInfo)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-04-19 00:00:06
 */
@Mapper
public interface VideoCommentsUserInfoMapper extends BaseMapper<VideoCommentsUserInfoEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCommentsUserInfoEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param videoCommentsUserInfo 查询条件
     * @return 对象列表
     */
    List<VideoCommentsUserInfoEntity> queryAll(VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 统计总行数
     *
     * @param videoCommentsUserInfo 查询条件
     * @return 总行数
     */
    long count(VideoCommentsUserInfoEntity videoCommentsUserInfo);

    @Override
    /**
     * 新增数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 影响行数
     */
    int insert(VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCommentsUserInfoEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoCommentsUserInfoEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCommentsUserInfoEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoCommentsUserInfoEntity> entities);

    /**
     * 修改数据
     *
     * @param videoCommentsUserInfo 实例对象
     * @return 影响行数
     */
    int update(VideoCommentsUserInfoEntity videoCommentsUserInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    VideoCommentsUserInfoEntity queryByUid(int id);

    int like(@Param("uid") Long id, @Param("commentId") Long commentId);

    int unlike(@Param("uid") Long id, @Param("commentId") Long courseId);
}
