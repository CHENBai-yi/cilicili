package site.cilicili.frontend.advertising.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.advertising.domain.pojo.VideoAdvertisingEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-29 14:46:15
 */

/**
 * (VideoAdvertising)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-04-29 14:46:15
 */
@Mapper
public interface VideoAdvertisingMapper extends BaseMapper<VideoAdvertisingEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    VideoAdvertisingEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param videoAdvertising 查询条件
     * @return 对象列表
     */
    List<VideoAdvertisingEntity> queryAll(VideoAdvertisingEntity videoAdvertising);

    /**
     * 统计总行数
     *
     * @param videoAdvertising 查询条件
     * @return 总行数
     */
    long count(VideoAdvertisingEntity videoAdvertising);

    /**
     * 新增数据
     *
     * @param videoAdvertising 实例对象
     * @return 影响行数
     */
    int insert(VideoAdvertisingEntity videoAdvertising);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoAdvertisingEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<VideoAdvertisingEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<VideoAdvertisingEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<VideoAdvertisingEntity> entities);

    /**
     * 修改数据
     *
     * @param videoAdvertising 实例对象
     * @return 影响行数
     */
    int update(VideoAdvertisingEntity videoAdvertising);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
}


