package site.cilicili.backend.log.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.cilicili.backend.log.domain.dto.QueryLogRequest;
import site.cilicili.backend.log.service.SysLogOperationService;
import site.cilicili.common.util.R;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * (SysLogOperation) 表控制层
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:12:57
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("log")
@Tag(name = "(SysLogOperation) 表控制层")
public class SysLogOperationController {
    /**
     * 服务对象
     */
    private final SysLogOperationService sysLogOperationService;

    /**
     * 查询操作日志列表
     *
     * @param queryLogRequest 查询条件
     * @return 响应结果
     */
    @Operation(
            summary = "查询操作日志列表",
            parameters = {@Parameter(description = "id 主键")})
    @PostMapping("get-log-operation-list")
    public R getLogOperationList(@RequestBody @Validated QueryLogRequest queryLogRequest, Errors errors) {
        return Optional.of(errors.getFieldErrors())
                .filter(e -> !e.isEmpty())
                .map(e -> R.no(e.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining())))
                .orElse(this.sysLogOperationService.getLogOperationList(queryLogRequest));
    }
}
