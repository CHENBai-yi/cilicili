package site.cilicili.backend.config.service;

import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedBackendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.pojo.SysConfigBackendEntity;
import site.cilicili.common.util.R;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (SysConfigBackend)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:43
 */
public interface SysConfigBackendService extends IService<SysConfigBackendEntity> {

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
     * @param sysConfigBackend 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysConfigBackendEntity sysConfigBackend);

    /**
     * 新增数据
     *
     * @param sysConfigBackend 实例对象
     * @return 实例对象
     */
    R insert(final SysConfigBackendEntity sysConfigBackend);

    /**
     * 修改数据
     *
     * @param sysConfigBackend 实例对象
     * @return 实例对象
     */
    R update(final SysConfigBackendEntity sysConfigBackend);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R queryConfigBackendAll();

    R getConfigBackendList(QueryConfigRequest queryBackRequest);

    R configBackendAdd(AddConfigRequest addConfigRequest);

    R editConfigBackend(AuthUserDetails authUserDetails, EditedBackendConfigRequest editedFrontendConfigRequest);
}
