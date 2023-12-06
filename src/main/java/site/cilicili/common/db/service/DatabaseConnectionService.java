package site.cilicili.common.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;
import site.cilicili.common.util.R;

/**
 * (DatabaseConnection)表服务接口
 *
 * @author ChenBaiYi
 * @since 2023-12-06 09:24:06
 */
public interface DatabaseConnectionService extends IService<DatabaseConnection> {

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
     * @param databaseConnection 筛选条件
     * @return 查询结果
     */
    R queryAll(final DatabaseConnection databaseConnection);

    /**
     * 新增数据
     *
     * @param databaseConnection 实例对象
     * @return 实例对象
     */
    R insert(final DatabaseConnection databaseConnection);

    /**
     * 修改数据
     *
     * @param databaseConnection 实例对象
     * @return 实例对象
     */
    R update(final DatabaseConnection databaseConnection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Integer id);
}
       

