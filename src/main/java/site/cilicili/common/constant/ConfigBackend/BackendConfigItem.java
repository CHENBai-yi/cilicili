/**
 * PROJECT:cilicili
 * PACkAGE:site.cilicili.backend.user.service.impl
 * Date:2024/1/7 20:05
 * EMAIL: chenbaiyi.work@gmail.com
 *
 * @author BaiYiChen
 */
package site.cilicili.common.constant.ConfigBackend;

import lombok.Getter;

/**
 * @author cby
 */
@Getter
public enum BackendConfigItem {
    /**
     * 上传头像保存位置
     */
    UPLOADAVATARSAVEPATH("上传头像保存位置", "uploadAvatarSavePath", "config_item"),
    AVATAREXT("头像允许后缀", "avatarExt", "config_item"),
    AVATARMAXSIZE("头像最大上传(M)", "avatarMaxSize", "config_item");
    private final String des;
    private final String item;
    private final String key;

    BackendConfigItem(final String des, final String item, String key) {
        this.des = des;
        this.item = item;
        this.key = key;
    }
}
