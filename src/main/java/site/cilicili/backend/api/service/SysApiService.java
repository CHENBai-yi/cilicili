package site.cilicili.backend.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.backend.api.domain.dto.GetApiListRequest;
import site.cilicili.backend.api.domain.pojo.SysApiEntity;
import site.cilicili.common.util.R;

/**
 * (SysApi)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:42
 */
public interface SysApiService extends IService<SysApiEntity> {

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
     * @param sysApi 筛选条件
     * @return 查询结果
     */
    R queryAll(final SysApiEntity sysApi);

    /**
     * 新增数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    R insert(final SysApiEntity sysApi);

    /**
     * 修改数据
     *
     * @param sysApi 实例对象
     * @return 实例对象
     */
    R update(final SysApiEntity sysApi);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Long id);

    R getApiList(GetApiListRequest apiListRequest);
}
