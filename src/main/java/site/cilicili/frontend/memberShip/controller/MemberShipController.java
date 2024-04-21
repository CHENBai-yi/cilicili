package site.cilicili.frontend.memberShip.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.common.util.R;
import site.cilicili.frontend.memberShip.domain.dto.QueryMemberShipRequest;
import site.cilicili.frontend.memberShip.domain.pojo.MemberShipEntity;
import site.cilicili.frontend.memberShip.service.MemberShipService;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (MemberShip) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:55
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("memberShip")
@Tag(name = "(MemberShip) 表控制层")
public class MemberShipController {
    /**
     * 服务对象
     */
    private final MemberShipService memberShipService;

    /**
     * 全查询
     *
     * @param memberShip 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "memberShip 筛选条件")})
    @GetMapping
    public R queryAll(final MemberShipEntity memberShip) {
        return this.memberShipService.queryAll(memberShip);
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @Operation(
            summary = "通过主键查询单条数据",
            parameters = {@Parameter(description = "id 主键")})
    @GetMapping("{id}")
    public R queryById(final @PathVariable("id") Integer id) {
        return this.memberShipService.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param memberShip 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增数据",
            parameters = {@Parameter(description = "memberShip 实体")})
    @PostMapping
    public R add(final @RequestBody MemberShipEntity memberShip) {
        return this.memberShipService.insert(memberShip);
    }

    /**
     * 编辑数据
     *
     * @param memberShip 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "memberShipEntity 实体")})
    @PutMapping
    public R edit(final @RequestBody MemberShipEntity memberShip) {
        return this.memberShipService.update(memberShip);
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @DeleteMapping
    public R deleteById(final Integer id) {
        return this.memberShipService.deleteById(id);
    }

    @Operation(
            summary = "删除数据",
            parameters = {@Parameter(description = "id 主键")})
    @GetMapping("id")
    public R getMemberPosition(final @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return this.memberShipService.getMemberPosition(authUserDetails);
    }

    @GetMapping("detail")
    public R memberShipDetail(
            final @AuthenticationPrincipal AuthUserDetails authUserDetails,
            final @RequestParam(value = "id", required = false) Long uId) {
        return this.memberShipService.memberShipDetail(authUserDetails, uId);
    }

    @Operation(
            summary = "根据条件查询交易信息",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("member-ship-list")
    public R memberShipList(@RequestBody @Validated QueryMemberShipRequest queryMemberShipRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(memberShipService.getMemberShipList(queryMemberShipRequest));
    }
}
