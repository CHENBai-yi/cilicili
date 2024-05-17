package site.cilicili.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.cilicili.backend.user.service.SysUserOnlineService;
import site.cilicili.common.config.dynamicDb.dataSource.DbInitialization;
import site.cilicili.common.cron.CustomerTaskPool;
import site.cilicili.common.mvcConfig.LogOperationAspect;
import site.cilicili.common.util.DbUtils;
import site.cilicili.common.util.R;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author BaiYiChen
 */
@RestControllerAdvice
@RequiredArgsConstructor
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);
    private final SysUserOnlineService sysUserOnlineService;
    private final ObjectMapper objectMapper;
    private final HttpServletResponse response;
    private final HttpServletRequest request;
    private final LogOperationAspect logOperationAspect;
    private final DbInitialization dbInitialization;
    private final String TITLE = "尊敬的用户你好：";
    private final String CONTENT =
            """
                    很抱歉地通知大家，由于服务器停机，部分用户可能会下线。我们深感抱歉给大家带来的不便，我们的技术团队正在全力以赴解决问题。请您谅解并耐心等待，我们会尽快恢复服务器正常运行。再次为此给您带来的困扰致以诚挚的歉意。
                    """;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException exception) {
        logger.error(exception.getMessage());
        final R r = R.no(exception.getMessage());
        final ResponseEntity<Object> ok = ResponseEntity.ok(r);
        logOperationAspect.writeOperationLog(ok.getStatusCode().value(), request, exception.getLocalizedMessage(), r);
        return ok;
    }

    @ExceptionHandler({
            JwtException.class,
            UsernameNotFoundException.class,
            ServletException.class,
            AppException.RefreshTokenException.class
    })
    public void handleAppException(Exception exception) {
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        R r = null;
        try (final OutputStream writer = response.getOutputStream()) {
            if (exception instanceof JwtException e) {
                response.setStatus(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value());
                r = Optional.of(dbInitialization.isValid())
                        .filter(f -> f)
                        .map(f -> sysUserOnlineService.queryById(null, e.getMessage()))
                        .filter(sysUserOnline -> sysUserOnlineService.removeByNameOrToken(
                                sysUserOnline.getUsername(), sysUserOnline.getToken()))
                        .map(sysUserOnline ->
                                R.no(Error.TOKEN_INVALIED.getMessage()).setData("reload", true))
                        .orElse(R.no(Error.TOKEN_INVALIED.getMessage()));
                logOperationAspect.writeOperationLog(response.getStatus(), request, exception.getLocalizedMessage(), r);
            } else if (exception instanceof UsernameNotFoundException e) {
                response.setStatus(HttpStatus.OK.value());
                r = R.no(e.getMessage()).setData("reload", true);
                logOperationAspect.writeOperationLog(response.getStatus(), request, exception.getLocalizedMessage(), r);
            } else if (exception instanceof ServletException e) {
                response.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
                r = R.no(e.getMessage()).setData("reload", true);
                logOperationAspect.writeOperationLog(response.getStatus(), request, exception.getLocalizedMessage(), r);
            } else if (exception instanceof AppException.RefreshTokenException e) {
                response.setStatus(HttpStatus.OK.value());
                response.addHeader("Access-Control-Expose-Headers", "refresh-token");
                response.addHeader("refresh-token", e.getNewToken());
                r = R.yes("token已刷新").setData("refresh", true);
            } else {
                response.setStatus(HttpStatus.OK.value());
                r = R.no(exception.getMessage());
            }
            writer.write(objectMapper.writeValueAsString(r).getBytes(StandardCharsets.UTF_8));
            writer.flush();
        } catch (IOException e) {
            logger.error("{} {}", exception.getMessage(), e.getMessage());
            throw new AppException(Error.COMMON_EXCEPTION);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessages> handleValidationError(MethodArgumentNotValidException exception) {
        logger.error(exception.getMessage());
        List<String> messages = Collections.emptyList();
        if (exception.getBindingResult() != null) {
            messages = exception.getBindingResult().getFieldErrors().stream()
                    .map(this::createFieldErrorMessage)
                    .collect(Collectors.toList());
        }
        final ResponseEntity<ErrorMessages> responseErrorMessages = responseErrorMessages(messages);
        logOperationAspect.writeOperationLog(
                responseErrorMessages.getStatusCode().value(),
                request,
                exception.getLocalizedMessage(),
                responseErrorMessages);
        return responseErrorMessages;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessages> handleException(Exception exception) {
        // return responseErrorMessages(List.of("internal server error"), HttpStatus.UNPROCESSABLE_ENTITY);
        logger.error(exception.getMessage());
        final ResponseEntity<ErrorMessages> responseErrorMessages =
                responseErrorMessages(List.of(Optional.ofNullable(exception.getMessage())
                        .orElse(Optional.ofNullable(exception.getCause())
                                .map(Throwable::getMessage)
                                .orElse(Error.COMMON_EXCEPTION.getMessage()))));
        logOperationAspect.writeOperationLog(
                responseErrorMessages.getStatusCode().value(),
                request,
                exception.getLocalizedMessage(),
                responseErrorMessages);
        return responseErrorMessages;
    }

    private ResponseEntity<ErrorMessages> responseErrorMessages(List<String> messages) {
        ErrorMessages errorMessages = new ErrorMessages();
        messages.forEach(errorMessages::append);
        return new ResponseEntity<>(errorMessages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private String createFieldErrorMessage(FieldError fieldError) {
        return "[" + fieldError.getField()
                + "] must be "
                + fieldError.getDefaultMessage()
                + ". your input: ["
                + fieldError.getRejectedValue()
                + "]";
    }

    /**
     * 服务器下线前对所有用户强制下线处理
     */
    // @PreDestroy
    public void preDestroy() {
        Optional.ofNullable(DbUtils.checkDb(dbInitialization.getDbChangeConf().getBackendInner()))
                .filter(r -> sysUserOnlineService.dropOnline(null))
                .ifPresent(r -> {
                    logger.error("服务器停机，强制下线所有用户");
                    final CustomerTaskPool.TaskEnum sysMessage = CustomerTaskPool.TaskEnum.SYS_MESSAGE;
                    sysMessage.setNoticeSent(CustomerTaskPool.NoticeSent.YES);
                    sysMessage.setToUser(CustomerTaskPool.NoticeToUserType.ALL);
                    sysMessage.setNoticeType(CustomerTaskPool.NoticeType.SYSTEM);
                    sysMessage.setTitle(TITLE);
                    sysMessage.setContent(CONTENT);
                    sysMessage.run();
                });
    }
}
