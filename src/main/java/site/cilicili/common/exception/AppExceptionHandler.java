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
import site.cilicili.common.mvcConfig.LogOperationAspect;
import site.cilicili.common.util.R;

import java.io.IOException;
import java.io.PrintWriter;
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
        try (final PrintWriter writer = response.getWriter()) {
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
            writer.write(objectMapper.writeValueAsString(r));
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
                responseErrorMessages(List.of(exception.getMessage()));
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
}
