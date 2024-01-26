package site.cilicili.backend.todo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import site.cilicili.authentication.Details.AuthUserDetails;
import site.cilicili.backend.todo.domain.dto.GetTodoListRequest;
import site.cilicili.backend.todo.domain.pojo.SysTodoEntity;
import site.cilicili.backend.todo.service.SysTodoService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysTodo) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-01-26 20:16:40
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("todo")
@Tag(name = "(SysTodo) 表控制层")
public class SysTodoController {
    /**
     * 服务对象
     */
    private final SysTodoService sysTodoService;

    /**
     * 全查询
     *
     * @param sysTodo 筛选条件
     * @return 查询结果
     */
    @Operation(
            summary = "全查询",
            parameters = {@Parameter(description = "sysTodo 筛选条件")})
    @GetMapping
    public R queryAll(final SysTodoEntity sysTodo) {
        return this.sysTodoService.queryAll(sysTodo);
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
    @PostMapping("query-todo-by-id")
    public R queryById(final @RequestBody SysTodoEntity id) {
        return this.sysTodoService.queryById(id);
    }

    /**
     * 编辑数据
     *
     * @param sysTodo 实体
     * @return 编辑结果
     */
    @Operation(
            summary = "编辑数据",
            parameters = {@Parameter(description = "sysTodoEntity 实体")})
    @PostMapping("edit-todo")
    public R edit(@AuthenticationPrincipal AuthUserDetails authUserDetails, final @RequestBody SysTodoEntity sysTodo) {
        return this.sysTodoService.update(authUserDetails, sysTodo);
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
    @PostMapping("delete-todo-by-id")
    public R deleteById(@AuthenticationPrincipal AuthUserDetails authUserDetails, final @RequestBody SysTodoEntity id) {
        return this.sysTodoService.deleteByTolistId(authUserDetails, id);
    }

    /**
     * 新增待办
     *
     * @param sysTodo 实体
     * @return 新增结果
     */
    @Operation(
            summary = "新增待办",
            parameters = {@Parameter(description = "sysTodo 实体")})
    @PostMapping("add-todo")
    public R add(final @RequestBody SysTodoEntity sysTodo) {
        return this.sysTodoService.insert(sysTodo);
    }

    /**
     * 按条件查询待办
     *
     * @param getTodoListRequest 实体
     * @return 待办列表结果
     */
    @Operation(
            summary = "按条件查询待办",
            parameters = {@Parameter(description = "sysTodo 实体")})
    @PostMapping("get-todo-list")
    public R getTodoList(final @RequestBody GetTodoListRequest getTodoListRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(sysTodoService.getTodoList(getTodoListRequest));
    }
}
