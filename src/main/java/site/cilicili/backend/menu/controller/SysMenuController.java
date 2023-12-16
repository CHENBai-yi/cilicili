package site.cilicili.backend.menu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.menu.service.SysMenuService;

/**
 * (SysMenu) 表控制层
 *
 * @author ChenBaiYi
 * @since 2023-12-14 04:01:59
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("public")
@Tag(name = "(SysMenu) 表控制层")
public class SysMenuController {
    /**
     * 服务对象
     */

    private final SysMenuService sysMenuService;

}



