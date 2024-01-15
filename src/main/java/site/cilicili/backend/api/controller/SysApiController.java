package site.cilicili.backend.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.api.domain.dto.AddApiRequest;
import site.cilicili.backend.api.domain.dto.DeleteOrQueryApiRequest;
import site.cilicili.backend.api.domain.dto.GetApiListRequest;
import site.cilicili.backend.api.domain.dto.SysApiDto;
import site.cilicili.backend.api.service.SysApiService;
import site.cilicili.common.util.R;

import java.util.stream.Collectors;

/**
 * (SysApi) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-30 15:21:43
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api")
@Tag(name = "(SysApi) 表控制层")
public class SysApiController {
    /**
     * 服务对象
     */
    private final SysApiService sysApiService;

    /**
     * 查api列表
     *
     * @param apiListRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @PostMapping("get-api-list")
    public R getApiList(@RequestBody final GetApiListRequest apiListRequest) {
        return sysApiService.getApiList(apiListRequest);
    }

    /**
     * 添加api
     *
     * @param addApiRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "添加api",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @PostMapping("add-api")
    public R addApi(@RequestBody @Validated final AddApiRequest addApiRequest, Errors errors) {
        if (!errors.getFieldErrors().isEmpty()) {
            return R.no(errors.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining()));
        }
        return sysApiService.addApi(addApiRequest);
    }

    /**
     * 删除api
     *
     * @param deleteOrQueryApiRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "删除api",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @PostMapping("delete-api-by-id")
    public R deleteApiById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final DeleteOrQueryApiRequest deleteOrQueryApiRequest) {
        return sysApiService.deleteApiById(authUserDetails, deleteOrQueryApiRequest);
    }

    /**
     * 根据id查询api
     *
     * @param deleteOrQueryApiRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "根据id查询api",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @PostMapping("query-api-by-id")
    public R queryApiById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final DeleteOrQueryApiRequest deleteOrQueryApiRequest) {
        return sysApiService.queryApiById(authUserDetails, deleteOrQueryApiRequest);
    }

    /**
     * 编辑api
     *
     * @param editRequest 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "编辑api",
            parameters = {@Parameter(description = "sysButton 筛选条件")})
    @PostMapping("edit-api")
    public R editApi(@RequestBody @Validated final SysApiDto.Records editRequest, @AuthenticationPrincipal AuthUserDetails authUserDetails) {
        return sysApiService.editApi(editRequest, authUserDetails);
    }
}
