package site.cilicili.backend.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryParamResponse;
import site.cilicili.backend.notice.domain.dto.NoticeListQueryRequest;
import site.cilicili.backend.notice.domain.pojo.SysNoticeEntity;

import java.util.List;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-25 15:55:20
 */

/**
 * (SysNotice)表数据库访问层
 *
 * @author ChenBaiYi
 * @since 2024-01-25 15:55:20
 */
@Mapper
public interface SysNoticeMapper extends BaseMapper<SysNoticeEntity> {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysNoticeEntity queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param sysNotice 查询条件
     * @return 对象列表
     */
    List<SysNoticeEntity> queryAll(SysNoticeEntity sysNotice);

    /**
     * 统计总行数
     *
     * @param sysNotice 查询条件
     * @return 总行数
     */
    long count(SysNoticeEntity sysNotice);

    /**
     * 新增数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int insert(SysNoticeEntity sysNotice);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeEntity> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SysNoticeEntity> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SysNoticeEntity> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SysNoticeEntity> entities);

    /**
     * 修改数据
     *
     * @param sysNotice 实例对象
     * @return 影响行数
     */
    int update(SysNoticeEntity sysNotice);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    List<NoticeListQueryParamResponse.Records> selectNoticeListByParam(NoticeListQueryRequest noticeListQueryRequest);

    List<NoticeListQueryParamResponse.Records> selectNoticeCountByParam(NoticeListQueryRequest noticeListQueryRequest);
}
