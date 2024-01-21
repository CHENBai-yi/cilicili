package site.cilicili.backend.menu.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.button.domain.pojo.SysButtonEntity;
import site.cilicili.backend.button.service.SysButtonService;
import site.cilicili.backend.menu.domain.dto.AddMenuRequest;
import site.cilicili.backend.menu.domain.dto.FindOrDeleteRequest;
import site.cilicili.backend.menu.domain.dto.GetMenuListRequest;
import site.cilicili.backend.menu.domain.dto.SysMenuDto;
import site.cilicili.backend.menu.domain.pojo.SysMenuEntity;
import site.cilicili.backend.menu.mapper.SysMenuMapper;
import site.cilicili.backend.menu.service.SysMenuService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Objects;
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

    private final SysButtonService sysButtonService;

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

    @Override
    @Transactional(readOnly = true)
    public R queryMenuById(final FindOrDeleteRequest findOrDeleteRequest) {
        return Optional.ofNullable(baseMapper.queryMenuById(findOrDeleteRequest.id()))
                .map(menu -> R.yes("查找成功.").setRecords(menu))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    @Caching(evict = {@CacheEvict(key = "'getMenuList'"), @CacheEvict(key = "#root.args[0].roleCode")})
    public R addMenu(final AuthUserDetails authUserDetails, final AddMenuRequest addMenuRequest) {
        return Optional.of(saveOrUpdate(BeanUtil.toBean(addMenuRequest, SysMenuEntity.class)))
                .filter(flag -> flag)
                .map(flag -> R.yes("添加成功."))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Caching(evict = {@CacheEvict(key = "'getMenuList'"), @CacheEvict(key = "#root.args[0].roleCode")})
    @Transactional(rollbackFor = Throwable.class)
    public R editMenu(final AuthUserDetails authUserDetails, final SysMenuDto.EditMenuRequest addMenuRequest) {
        return Optional.ofNullable(authUserDetails)
                .filter(auth -> Objects.nonNull(getById(addMenuRequest.getId()))
                        && ((Objects.nonNull(addMenuRequest.getButton())
                        && !addMenuRequest.getButton().isEmpty()
                        && sysButtonService.insertOrUpdate(addMenuRequest.getButton()))
                        | updateById(addMenuRequest)))
                .map(auth -> R.yes(String.format("%1$s编辑成功.", auth.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

    @Override
    @Caching(evict = {@CacheEvict(key = "'getMenuList'"), @CacheEvict(key = "#root.args[0].roleCode")})
    @Transactional(rollbackFor = Throwable.class)
    public R deleteMenuById(final AuthUserDetails authUserDetails, final FindOrDeleteRequest findOrDeleteRequest) {
        return Optional.ofNullable(authUserDetails)
                .map(auth -> baseMapper.selectById(findOrDeleteRequest.id()))
                .filter(sysMenuEntity -> !"yesNo_yes".equals(sysMenuEntity.getStable()))
                .filter(sysMenuEntity -> sysButtonService.remove(
                        new QueryWrapper<SysButtonEntity>().eq("menu_name", sysMenuEntity.getName()))
                        | remove(new QueryWrapper<SysMenuEntity>().eq("parent_code", sysMenuEntity.getName()))
                        | removeById(sysMenuEntity))
                .map(auth -> R.yes(String.format("%1$s删除成功.", authUserDetails.getusername())))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
