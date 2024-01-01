package site.cilicili.common.exception;

import lombok.Getter;

/**
 * @author BaiYiChen
 */
@Getter
public class AppException extends RuntimeException {
    private final Error error;

    public AppException(Error error) {
        super(error.getMessage());
        this.error = error;
    }
}
