package site.cilicili.backend.log.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.pojo.SysLogOperationEntity;
import site.cilicili.common.util.R;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (SysLogOperation)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:12:57
 */
public interface SysLogOperationService extends IService<SysLogOperationEntity> {

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
     * @param sysLogOperation 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysLogOperationEntity sysLogOperation);

    /**
     * 新增数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    R insert(final SysLogOperationEntity sysLogOperation);

    /**
     * 修改数据
     *
     * @param sysLogOperation 实例对象
     * @return 实例对象
     */
    R update(final SysLogOperationEntity sysLogOperation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getLogOperationList(QueryLogRequest queryLogRequest);

    List<Map<String, Object>> getPvData(DateTime start, Date end);
}
