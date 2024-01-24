package site.cilicili.backend.config.service;

import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.config.domain.dto.AddConfigRequest;
import site.cilicili.backend.config.domain.dto.EditedFrontendConfigRequest;
import site.cilicili.backend.config.domain.dto.QueryConfigRequest;
import site.cilicili.backend.config.domain.pojo.SysConfigFrontendEntity;
import site.cilicili.common.util.R;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * (SysConfigFrontend)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-16 14:52:44
 */
public interface SysConfigFrontendService extends IService<SysConfigFrontendEntity> {

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
     * @param sysConfigFrontend 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 新增数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 实例对象
     */
    R insert(final SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 修改数据
     *
     * @param sysConfigFrontend 实例对象
     * @return 实例对象
     */
    R update(final SysConfigFrontendEntity sysConfigFrontend);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R queryConfigFrontAll();

    R getConfigFrontendList(QueryConfigRequest queryFrontendRequest);

    R configFrontendAdd(AddConfigRequest addConfigRequest);

    R editConfigFrontend(
            final AuthUserDetails authUserDetails, EditedFrontendConfigRequest editedFrontendConfigRequest);
}
