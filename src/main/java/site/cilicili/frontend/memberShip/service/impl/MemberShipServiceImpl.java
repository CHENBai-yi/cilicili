package site.cilicili.frontend.memberShip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.authentication.user.entity.UserEntity;
import site.cilicili.backend.log.domain.dto.QueryLogResponse;
import site.cilicili.common.constant.pay.AliPayStatus;
import site.cilicili.common.exception.AppException;
import site.cilicili.common.exception.Error;
import site.cilicili.common.util.R;
import site.cilicili.frontend.memberShip.domain.dto.MemberShipDto;
import site.cilicili.frontend.memberShip.domain.dto.QueryMemberShipRequest;
import site.cilicili.frontend.memberShip.domain.pojo.MemberShipEntity;
import site.cilicili.frontend.memberShip.mapper.MemberShipMapper;
import site.cilicili.frontend.memberShip.service.MemberShipService;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * (MemberShip) 表服务实现类
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:55
 */
@RequiredArgsConstructor
@Transactional(rollbackFor = Throwable.class)
@Service("memberShipService")
public class MemberShipServiceImpl extends ServiceImpl<MemberShipMapper, MemberShipEntity>
        implements MemberShipService {

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
     * @param memberShip 筛选条件
     * @return 查询结果
     */
    @Override
    public R queryAll(MemberShipEntity memberShip) {
        return R.ok().setData(baseMapper.queryAll(memberShip));
    }

    /**
     * 新增数据
     *
     * @param memberShip 实例对象
     * @return 实例对象
     */
    @Override
    public R insert(MemberShipEntity memberShip) {
        baseMapper.insert(memberShip);
        return R.ok().setData(memberShip);
    }

    /**
     * 修改数据
     *
     * @param memberShip 实例对象
     * @return 实例对象
     */
    @Override
    public R update(MemberShipEntity memberShip) {
        baseMapper.update(memberShip);
        return R.ok().setData(this.queryById(Math.toIntExact(memberShip.getId())));
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

    @Transactional(readOnly = true)
    @Override
    public Boolean isMember(final Long id, final String username) {
        return Objects.nonNull(baseMapper.queryByIdOrUsername(id, username));
    }

    @Override
    public R payNotify(final Map<String, String> params) {
        return Optional.ofNullable(params)
                .filter(data -> Objects.nonNull(params.get("trade_status"))
                        && AliPayStatus.SUCCESS.getStatus().equals(params.get("trade_status")))
                .map(r -> {
                    final String passbackParams = r.get("passback_params");
                    return Optional.ofNullable(JSONUtil.toBean(
                                    JSONUtil.parseObj(URLDecoder.decode(passbackParams, StandardCharsets.UTF_8)),
                                    UserEntity.class))
                            .map(authUserDetails -> {
                                final MemberShipEntity entity = new MemberShipEntity();
                                entity.setProduct(r.get("body"));
                                entity.setTradeNo(r.get("trade_no"));
                                entity.setStatus(r.get("trade_status"));
                                entity.setUserId(authUserDetails.getId());
                                entity.setMethod("支付宝");
                                entity.setPrice(BigDecimal.valueOf(
                                        Double.parseDouble(Optional.ofNullable(r.get("receipt_amount"))
                                                .orElse("0.0"))));
                                entity.setUsername(authUserDetails.getUsername());
                                return baseMapper.insert(entity) > 0 ? R.yes("支付成功！") : R.no("订单插入数据库异常！");
                            })
                            .orElse(R.no("支付失败！"));
                })
                .orElse(R.no("支付失败"));
    }

    @Transactional(readOnly = true)
    @Override
    public R getMemberPosition(final AuthUserDetails authUserDetails) {
        return Optional.ofNullable(authUserDetails)
                .map(authUserDetails1 -> baseMapper.getMemberPosition(authUserDetails1.getId()))
                .map(r -> R.yes(null).setData(r))
                .orElse(R.no("您还未登录哦！！！"));
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean checkIsAlreadyMemberShip(final Long id) {
        return Objects.isNull(baseMapper.queryByIdOrUsername(id, null));
    }

    @Transactional(readOnly = true)
    @Override
    public R memberShipDetail(final AuthUserDetails authUserDetails, final Long uId) {
        final Long id = Optional.ofNullable(uId).orElse(authUserDetails.getId());
        return R.yes("Success.").setData(BeanUtil.toBean(baseMapper.queryByUserId(id), MemberShipDto.class));
    }

    @Transactional(readOnly = true)
    @Override
    public R getMemberShipList(final QueryMemberShipRequest queryMemberShipRequest) {
        return Optional.ofNullable(baseMapper.queryMemberShipList(queryMemberShipRequest))
                .map(userList -> R.yes("Success")
                        .setData(QueryLogResponse.builder()
                                .total(userList.size())
                                .page(queryMemberShipRequest.page())
                                .pageSize(queryMemberShipRequest.pageSize())
                                .records(userList)
                                .build()))
                .orElseThrow(() -> new AppException(Error.COMMON_EXCEPTION));
    }
}
