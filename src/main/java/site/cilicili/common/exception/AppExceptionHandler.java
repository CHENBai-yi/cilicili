package site.cilicili.common.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.cilicili.common.util.R;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author BaiYiChen
 */
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException exception) {
        return ResponseEntity.ok(R.no(exception.getMessage()));
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Object> handleAppException(JwtException exception) {
        return ResponseEntity.ok(R.no(Error.TOKEN_INVALIED.getMessage()).setData("reload", true));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessages> handleValidationError(MethodArgumentNotValidException exception) {
        List<String> messages = exception.getBindingResult().getFieldErrors().stream()
                .map(this::createFieldErrorMessage)
                .collect(Collectors.toList());
        return responseErrorMessages(messages, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessages> handleException(Exception exception) {
        return responseErrorMessages(List.of("internal server error"), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ResponseEntity<ErrorMessages> responseErrorMessages(List<String> messages, HttpStatus status) {
        ErrorMessages errorMessages = new ErrorMessages();
        messages.forEach(errorMessages::append);
        return new ResponseEntity<>(errorMessages, status);
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
