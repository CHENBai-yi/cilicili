package site.cilicili.frontend.carousel.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.carousel.domain.dto.VideoCarouselDto;
import site.cilicili.frontend.carousel.domain.pojo.VideoCarouselEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:45:45
 */

/**
 * (VideoCarousel)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:45:45
 */
@Mapper
public interface VideoCarouselMapper extends BaseMapper<VideoCarouselEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoCarouselEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param videoCarousel 查询条件
     * @return 对象列表
     */
    List<VideoCarouselEntity> queryAll(VideoCarouselEntity videoCarousel);

    /**
     * 统计总行数
     *
     * @param videoCarousel 查询条件
     * @return 总行数
     */
    Long count(VideoCarouselEntity videoCarousel);

    /**
     * 新增数据
     *
     * @param videoCarousel 实例对象
     * @return 影响行数
     */
    int insert(VideoCarouselEntity videoCarousel);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCarouselEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoCarouselEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoCarouselEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoCarouselEntity> entities);

    /**
     * 修改数据
     *
     * @param videoCarousel 实例对象
     * @return 影响行数
     */
    int update(VideoCarouselEntity videoCarousel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<VideoCarouselEntity> queryAllByParam(VideoCarouselDto videoCarousel);
}


