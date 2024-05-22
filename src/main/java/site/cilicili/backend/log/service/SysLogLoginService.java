package site.cilicili.backend.log.service;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.log.domain.dto.DeleteLogRequest;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.domain.pojo.SysLogLoginEntity;
import site.cilicili.common.util.R;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (SysLogLogin)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:43
 */
public interface SysLogLoginService extends IService<SysLogLoginEntity> {

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
     * @param sysLogLogin 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysLogLoginEntity sysLogLogin);

    /**
     * 新增数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    R insert(final SysLogLoginEntity sysLogLogin);

    /**
     * 修改数据
     *
     * @param sysLogLogin 实例对象
     * @return 实例对象
     */
    R update(final SysLogLoginEntity sysLogLogin);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getLogLoginList(QueryLogRequest queryLogRequest);

    R deleteLogLoginById(final AuthUserDetails authUserDetails, DeleteLogRequest deleteLogRequest);

    List<Map<String, Object>> getIpData(DateTime start, Date end);

    List<Map<String, Object>> getUvData(DateTime start, Date end);
}
