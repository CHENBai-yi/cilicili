package site.cilicili.common.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.cilicili.common.db.dao.DatabaseConnectionMapper;
import site.cilicili.common.db.domain.pojo.DatabaseConnection;
import site.cilicili.common.db.service.DatabaseConnectionService;
import site.cilicili.common.util.R;

/**
 * (DatabaseConnection)表服务实现类
 *
 * @author ChenBaiYi
 * @since 2023-12-06 09:24:06
 */
@RequiredArgsConstructor
@Service("databaseConnectionService")
public class DatabaseConnectionServiceImpl extends ServiceImpl<DatabaseConnectionMapper, DatabaseConnection> implements DatabaseConnectionService {

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
     * @param databaseConnection 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(DatabaseConnection databaseConnection) {
        return R.ok().setData(baseMapper.queryAll(databaseConnection));
    }

    /**
     * 新增数据
     *
     * @param databaseConnection 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(DatabaseConnection databaseConnection) {
        baseMapper.insert(databaseConnection);
        return R.ok().setData(databaseConnection);
    }

    /**
     * 修改数据
     *
     * @param databaseConnection 实例对象
     * @return 实例对象
     */
    @Override
    public R update(DatabaseConnection databaseConnection) {
        baseMapper.update(databaseConnection);
        return R.ok().setData(this.queryById(databaseConnection.getId()));
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

}


