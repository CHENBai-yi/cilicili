package site.cilicili.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author BaiYiChen
 */
@Getter
public enum Error {
    DUPLICATED_USER("there is duplicated user information", HttpStatus.UNPROCESSABLE_ENTITY),
    LOGIN_INFO_INVALID("login information is invalid", HttpStatus.UNPROCESSABLE_ENTITY),
    ALREADY_FOLLOWED_USER("already followed user", HttpStatus.UNPROCESSABLE_ENTITY),
    ALREADY_FAVORITED_ARTICLE("already followed user", HttpStatus.UNPROCESSABLE_ENTITY),

    USER_NOT_FOUND("user not found", HttpStatus.NOT_FOUND),
    FOLLOW_NOT_FOUND("such follow not found", HttpStatus.NOT_FOUND),
    ARTICLE_NOT_FOUND("article not found", HttpStatus.NOT_FOUND),
    FAVORITE_NOT_FOUND("favorite not found", HttpStatus.NOT_FOUND),
    COMMENT_NOT_FOUND("comment not found", HttpStatus.NOT_FOUND),
    COMMON_EXCEPTION("服务器有点呆。。。", HttpStatus.NOT_FOUND),
    EXCEED_UPLOAD_MAXSIZE("上传的文件太大.", HttpStatus.NOT_FOUND),
    TOKEN_INVALIED("token已失效，登录过期", HttpStatus.NOT_FOUND),
    ALREADY_LOGIN("该账号已在别处登录！", HttpStatus.NOT_FOUND),
    AVATAREXT_DISALLOW("不支持的文件后缀.", HttpStatus.NOT_FOUND),
    KICKED_USER("您已被强制下线！", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus status;

    Error(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
