package site.cilicili.common.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.pay
 * Date:2024/4/14 19:56
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
@ConfigurationProperties(prefix = "alipay")
@PropertySource(value = {"classpath:aliPay.properties"}, encoding = "UTF-8")
@Data
@Component
@Slf4j
public class AlipayTemplate {
    public String desc;
    public String subject;
    public String price;
    //↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
    public String timeExpire;
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public String appId;

    // 商户私钥，您的PKCS8格式RSA2私钥
    public String merchantPrivateKey;
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public String alipayPublicKey;

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String notifyUrl;

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public String returnUrl;

    // 签名方式
    public String signType;

    // 字符编码格式
    public String charset;
    public String dataType;

    // 支付宝网关
    // public String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    public String gatewayUrl;
    public String logPath;
    //↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     *
     * @param sWord 要写入日志里的文本内容
     */
    public void logResult(String sWord) {
        FileWriter writer = null;
        try {
            System.out.println("日志-->>" + sWord);
            writer = new FileWriter(AlipayTemplate.this.logPath + "alipay_log_" + System.currentTimeMillis() + ".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                log.error(e.getMessage());
            }

        }
    }

    public String pay(AlipayTradeAppPayModel model) throws AlipayApiException {
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                gatewayUrl,
                appId,
                merchantPrivateKey,
                dataType,
                charset,
                alipayPublicKey,
                signType);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setBizModel(model);
        String body = alipayClient.pageExecute(alipayRequest).getBody();
        logResult(body);
        return body;
    }
}
