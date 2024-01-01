package site.cilicili.backend.dept.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.backend.dept.domain.dto.GetDeptListRequest;
import site.cilicili.backend.dept.domain.dto.SysDeptDto;
import site.cilicili.backend.dept.domain.pojo.SysDeptEntity;
import site.cilicili.backend.dept.mapper.SysDeptMapper;
import site.cilicili.backend.dept.service.SysDeptService;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;

import java.util.Optional;

/**
 * (SysDept) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-30 16:36:33
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDeptEntity> implements SysDeptService {

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
     * @param sysDept 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SysDeptEntity sysDept) {
        return R.ok().setData(baseMapper.queryAll(sysDept));
    }

    /**
     * 新增数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SysDeptEntity sysDept) {
        baseMapper.insert(sysDept);
        return R.ok().setData(sysDept);
    }

    /**
     * 修改数据
     *
     * @param sysDept 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SysDeptEntity sysDept) {
        baseMapper.update(sysDept);
        return R.ok().setData(this.queryById(sysDept.getId()));
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
    public R getDeptList(final GetDeptListRequest deptListRequest) {
        return Optional.ofNullable(baseMapper.getDeptList(deptListRequest)).map(deptListDtos -> R.yes("Success").setData(
                SysDeptDto.builder().page(deptListRequest.page()).pageSize(deptListRequest.pageSize())
                        .total(deptListDtos.size() + deptListDtos.get(0).getChildren().size()).records(deptListDtos).build())
        ).orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }

}


