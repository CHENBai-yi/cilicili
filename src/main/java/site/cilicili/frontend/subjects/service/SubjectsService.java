package site.cilicili.frontend.subjects.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.subjects.domain.dto.GetSubjectListRequest;
import site.cilicili.frontend.subjects.domain.pojo.SubjectsEntity;

/**
 * (Subjects)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:39
 */
public interface SubjectsService extends IService<SubjectsEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    R queryById(final Integer id);

    /**
     * 全查询
     *
     * @param subjects 筛选条件
     * @return 查询结果
     */
    R queryAll(final SubjectsEntity subjects);

    /**
     * 新增数据
     *
     * @param subjects 实例对象
     * @return 实例对象
     */
    R insert(final SubjectsEntity subjects);

    /**
     * 修改数据
     *
     * @param subjects 实例对象
     * @return 实例对象
     */
    R update(final SubjectsEntity subjects);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Integer id);

    R addSubject(SubjectsEntity subjects);

    R getSubjectList(GetSubjectListRequest subjects);

    R deleteSubjectById(SubjectsEntity id);
}
       

