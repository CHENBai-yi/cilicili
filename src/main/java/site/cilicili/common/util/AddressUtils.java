package site.cilicili.common.util;

/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.common.util
 * Date:2024/4/18 22:55
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据IP获取地址信息
 *
 * @author zhangqi
 * @date 2022/7/3 20:32
 */
@SuppressWarnings("unused")
@Slf4j
public class AddressUtils {
    /**
     * IP地址查询
     */
    private static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";
    /**
     * 未知地址
     */
    private static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (internalIp(ip)) {
            return "内网IP";
        }
        try (final HttpResponse httpResponse = HttpUtil.createGet(IP_URL + "ip=" + ip + "&json=true").charset(CharsetUtil.CHARSET_GBK).execute()) {
            if (!httpResponse.isOk()) {
                return UNKNOWN;
            }
            String body = httpResponse.body();
            if (StringUtils.isEmpty(body)) {
                return UNKNOWN;
            }
            JSONObject obj = JSON.parseObject(body);
            String region = obj.getString("pro");
            String city = obj.getString("city");
            return String.format("%s %s", region, city);
        } catch (Exception e) {
            log.error("获取地理位置异常 {}", ip);
        }
        return UNKNOWN;
    }

    /**
     * 检查是否为内部IP地址
     *
     * @param ip IP地址
     * @return 结果
     */
    public static boolean internalIp(String ip) {
        byte[] addr = textToNumericFormatV4(ip);
        return internalIp(addr) || "127.0.0.1".equals(ip);
    }

    /**
     * 检查是否为内部IP地址
     *
     * @param addr byte地址
     * @return 结果
     */
    private static boolean internalIp(byte[] addr) {
        if (ArrayUtils.isEmpty(addr) || addr.length < 2) {
            return true;
        }
        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                return (b1 >= SECTION_3 && b1 <= SECTION_4);
            case SECTION_5:
                return (b1 == SECTION_6);
            default:
                return false;
        }
    }

    /**
     * 将IPv4地址转换成字节
     *
     * @param text IPv4地址
     * @return byte 字节
     */
    private static byte[] textToNumericFormatV4(String text) {
        if (text.length() == 0) {
            return new byte[0];
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1:
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L)) {
                        return new byte[0];
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 2:
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L)) {
                        return new byte[0];
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L)) {
                        return new byte[0];
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 3:
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return new byte[0];
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L)) {
                        return new byte[0];
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                    break;
                case 4:
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return new byte[0];
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    break;
                default:
                    return new byte[0];
            }
        } catch (NumberFormatException e) {
            return new byte[0];
        }
        return bytes;
    }


}


