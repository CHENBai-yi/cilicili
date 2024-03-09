package site.cilicili.frontend.subjects.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.subjects.domain.dto.GetSubjectListRequest;
import site.cilicili.frontend.subjects.domain.dto.GetSubjectListResponse;
import site.cilicili.frontend.subjects.domain.pojo.SubjectsEntity;
import site.cilicili.frontend.subjects.mapper.SubjectsMapper;
import site.cilicili.frontend.subjects.service.SubjectsService;

import java.util.Optional;

/**
 * (Subjects) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:39
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("subjectsService")
public class SubjectsServiceImpl extends ServiceImpl<SubjectsMapper, SubjectsEntity> implements SubjectsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public R queryById(Integer id) {
        return R.ok().setData(baseMapper.queryById(id));
    }

    /**
     * 全查询
     *
     * @param subjects 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(SubjectsEntity subjects) {
        return R.ok().setData(baseMapper.queryAll(subjects));
    }

    /**
     * 新增数据
     *
     * @param subjects 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(SubjectsEntity subjects) {
        baseMapper.insert(subjects);
        return R.ok().setData(subjects);
    }

    /**
     * 修改数据
     *
     * @param subjects 实例对象
     * @return 实例对象
     */
    @Override
    public R update(SubjectsEntity subjects) {
        baseMapper.update(subjects);
        return R.ok().setData(this.queryById(subjects.getId()));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public R deleteById(Integer id) {
        boolean del = baseMapper.deleteById(id) > 0;
        return R.ok().setData(del);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R addSubject(final SubjectsEntity subjects) {
        baseMapper.insert(subjects);
        return R.yes(String.format("%1$s添加成功.", subjects.getSubjectName()));
    }

    @Transactional(readOnly = true)
    @Override
    public R getSubjectList(final GetSubjectListRequest subjects) {
        return Optional.ofNullable(baseMapper.getSubjectList(subjects))
                .map(records -> {
                    final GetSubjectListResponse build = GetSubjectListResponse.builder()
                            .page(subjects.getPage())
                            .pageSize(subjects.getPageSize())
                            .total(records.size())
                            .build();
                    if (records.size() == 1) {
                        build.setRecords(records.get(0));
                    } else {
                        build.setRecords(records);
                    }
                    return R.yes("Success.").setData(build);
                })
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public R deleteSubjectById(final SubjectsEntity id) {
        return Optional.of(removeById(id))
                .filter(f -> f)
                .map(f -> R.yes("Success."))
                .orElse(R.no(Error.COMMON_EXCEPTION.getMessage()));
    }
}
