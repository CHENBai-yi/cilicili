package site.cilicili.backend.dict.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
