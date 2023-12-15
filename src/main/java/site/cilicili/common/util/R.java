package site.cilicili.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回对象R
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:48
 */
@Slf4j
@Data
@Schema(description = "统一返回对象R")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY, content = JsonInclude.Include.NON_EMPTY)
public class R implements Serializable {
    private static final long serialVersionUID = 659942594217762465L;
    @Schema(description = "响应编码")
    private int code;
    @Schema(description = "响应信息")
    private String message;
    @Schema(description = "响应数据")
    private Object data;

    /**
     * 操作成功
     *
     * @return R
     */
    public static R ok() {
        R r = new R();
        r.code = 200;
        r.message = "OK";
        return r;
    }

    /**
     * 认证授权失败。 包括密钥信息不正确；数字签名错误；授权已超时
     *
     * @return R
     */
    public static R fail() {
        R r = new R();
        r.code = 401;
        r.message = "fail";
        return r;
    }

    /**
     * 系统异常
     *
     * @return R
     */
    public static R exp() {
        R r = new R();
        r.code = 500;
        r.message = "exception";
        return r;
    }

    public static R yes(String message) {
        R r = new R();
        r.code = 1;
        if (message == null) {
            r.message = "OK";
        } else {
            r.message = message;
        }
        return r;
    }

    public static R no(String message) {
        R r = new R();
        r.code = 0;
        r.message = message;
        return r;
    }

    /**
     * 设置数据
     *
     * @param data 数据
     * @return R
     */
    public R setData(Object data) {
        this.data = data;
        return this;
    }

    public R setData(String key, Object val) {
        if (data == null) {
            data = new HashMap<String, Object>();
        }
        if (data instanceof Map) {
            final HashMap<String, Object> map = (HashMap<String, Object>) data;
            map.put(key, val);
        }
        return this;
    }
}


