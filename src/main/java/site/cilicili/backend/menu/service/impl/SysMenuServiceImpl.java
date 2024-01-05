package site.cilicili.backend.menu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.menu.domain.dto.GetMenuListRequest;
import site.cilicili.backend.menu.domain.dto.SysMenuDto;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.menu.mapper.SysMenuMapper;
import site.cilicili.backend.menu.service.SysMenuService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysMenu) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:01:59
 */
@RequiredArgsConstructor
@Service("sysMenuService")
@CacheConfig(cacheNames = {"SysMenu"})
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements SysMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Long id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param sysMenu 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysMenuEntity sysMenu) {
        return R.ok().setData(baseMapper.queryAll(sysMenu));
    }

    /**
     * 新增数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysMenuEntity sysMenu) {
        baseMapper.insert(sysMenu);
        return R.ok().setData(sysMenu);
    }

    /**
     * 修改数据
     *
     * @param sysMenu 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysMenuEntity sysMenu) {
        baseMapper.update(sysMenu);
        return R.ok().setData(this.queryById(sysMenu.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Long id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(key = "#root.methodName")
    public R getMenuList(final GetMenuListRequest sysMenuListRequest) {
        return Optional.ofNullable(baseMapper.getRoleMenuList(sysMenuListRequest))
                .map(records -> R.yes("Success")
                        .setData(SysMenuDto.builder()
                                .build()
                                .setRecords(records)
                                .setTotal(records.size())
                                .setPageNum(sysMenuListRequest.page())
                                .setPageSize(sysMenuListRequest.pageSize())))
                .orElse(R.no("Fail"));
    }

    @Cacheable(key = "#root.args[0].roleCode")
    @Transactional(readOnly = true)
    @Override
    public R getUserMenu(final AuthUserDetails authUserDetails) {
        return Optional.ofNullable(baseMapper.getUserMenu(authUserDetails.getusername()))
                .map(item -> R.yes("获取用户菜单成功.").setData(item))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
