package site.cilicili.frontend.memberShip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import site.cilicili.common.util.R;
import site.cilicili.frontend.memberShip.domain.pojo.MemberShipEntity;

import java.util.Map;

/**
 * (MemberShip)表服务接口
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:55
 */
public interface MemberShipService extends IService<MemberShipEntity> {

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
     * @param memberShip 筛选条件
     * @return 查询结果
     */
    R queryAll(final MemberShipEntity memberShip);

    /**
     * 新增数据
     *
     * @param memberShip 实例对象
     * @return 实例对象
     */
    R insert(final MemberShipEntity memberShip);

    /**
     * 修改数据
     *
     * @param memberShip 实例对象
     * @return 实例对象
     */
    R update(final MemberShipEntity memberShip);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    R deleteById(final Integer id);

    Boolean isMember(Long id, String getusername);

    R payNotify(Map<String, String> params);
}
       

