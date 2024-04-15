package site.cilicili.frontend.memberShip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.frontend.memberShip.domain.pojo.MemberShipEntity;

import java.util.List;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-13 20:20:54
 */

/**
 * (MemberShip)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:54
 */
@Mapper
public interface MemberShipMapper extends BaseMapper<MemberShipEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    MemberShipEntity queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param memberShip 查询条件
     * @return 对象列表
     */
    List<MemberShipEntity> queryAll(MemberShipEntity memberShip);

    /**
     * 统计总行数
     *
     * @param memberShip 查询条件
     * @return 总行数
     */
    long count(MemberShipEntity memberShip);

    @Override
    /**
     * 新增数据
     *
     * @param memberShip 实例对象
     * @return 影响行数
     */
    int insert(MemberShipEntity memberShip);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberShipEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<MemberShipEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<MemberShipEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<MemberShipEntity> entities);

    /**
     * 修改数据
     *
     * @param memberShip 实例对象
     * @return 影响行数
     */
    int update(MemberShipEntity memberShip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    MemberShipEntity queryByIdOrUsername(@Param("id") Long id, @Param("username") String username);

    Integer getMemberPosition(Long id);
}


