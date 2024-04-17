package site.cilicili.common.constant.pay;

import lombok.Getter;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.constant.pay
 * Date:2024/4/14 20:15
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@Getter
public enum AliPayStatus {
    SUCCESS("TRADE_SUCCESS"),
    FINISHED("TRADE_FINISHED"),
    PRODUCT("FAST_INSTANT_TRADE_PAY");
    private String status;

    AliPayStatus(final java.lang.String status) {
        this.status = status;
    }
}
