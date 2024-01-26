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

    public AppException(String eMsg) {
        super(eMsg);
        this.error = null;
    }

    @Getter
    public static class RefreshTokenException extends RuntimeException {
        private String newToken;

        public RefreshTokenException(final String message) {
            newToken = message;
        }
    }
}
