package site.cilicili.backend.dict.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.dict.domain.dto.AddDictRequest;
import site.cilicili.backend.dict.domain.dto.QueryAndDeleteRequest;
import site.cilicili.backend.dict.domain.dto.SysDictDto;
import site.cilicili.backend.dict.service.SysDictService;
import site.cilicili.common.util.R;

/**
 * (SysDict) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-16 12:44:36
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping({"public", "dict"})
@Tag(name = "(SysDict) 表控制层")
public class SysDictController {
    /**
     * 服务对象
     */
    private final SysDictService sysDictService;

    @PostMapping({"get-dict-all", "get-dict-list"})
    public R getDictAll() {
        return this.sysDictService.queryDictAll();
    }

    /**
     * 添加字典
     *
     * @return R
     */
    @PostMapping("add-dict")
    public R addDict(@RequestBody @Validated final AddDictRequest addDictRequest) {
        return sysDictService.addDict(addDictRequest);
    }

    /**
     * 通过id查询字典
     *
     * @return R
     */
    @PostMapping("query-dict-by-id")
    public R queryDictById(@RequestBody @Validated final QueryAndDeleteRequest queryAndDeleteRequest) {
        return sysDictService.queryDictById(queryAndDeleteRequest);
    }

    /**
     * 编辑字典
     *
     * @return R
     */
    @PostMapping("edit-dict")
    public R editDict(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final SysDictDto.AddOrEditResponse editRequest) {
        return sysDictService.editDict(authUserDetails, editRequest);
    }

    /**
     * 删除字典
     *
     * @return R
     */
    @PostMapping("delete-dict-by-id")
    public R deleteDictById(@AuthenticationPrincipal AuthUserDetails authUserDetails, @RequestBody @Validated final QueryAndDeleteRequest queryAndDeleteRequest) {
        return sysDictService.deleteDictById(authUserDetails, queryAndDeleteRequest);
    }
}
