package site.cilicili.frontend.bars.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.bars.domain.pojo.BarsEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:00:41
 */

/**
 * (Bars)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:00:41
 */
@Mapper
public interface BarsMapper extends BaseMapper<BarsEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param barId 主键
     * @return 实例对象
     */
    BarsEntity queryById(Integer barId);

    /**
     * 查询指定行数据
     *
     * @param bars 查询条件
     * @return 对象列表
     */
    List<BarsEntity> queryAll(BarsEntity bars);

    /**
     * 统计总行数
     *
     * @param bars 查询条件
     * @return 总行数
     */
    long count(BarsEntity bars);

    /**
     * 新增数据
     *
     * @param bars 实例对象
     * @return 影响行数
     */
    int insert(BarsEntity bars);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<BarsEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<BarsEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<BarsEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<BarsEntity> entities);

    /**
     * 修改数据
     *
     * @param bars 实例对象
     * @return 影响行数
     */
    int update(BarsEntity bars);

    /**
     * 通过主键删除数据
     *
     * @param barId 主键
     * @return 影响行数
     */
    int deleteById(Integer barId);
}


