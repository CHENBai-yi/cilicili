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
    UPLOADAVATARSAVEPATH("上传头像保存位置", "uploadAvatarSavePath", "cilicili", "config_item"),
    UPLOADBANNERIMAGESAVEPATH("上传首页大图保存位置", "uploadBannerImageSavePath", "bannerImage", "config_item"),
    UPLOADLOGOSAVEPATH("上传logo保存位置", "uploadLogoSavePath", "logo", "config_item"),
    UPLOADFAVICONSAVEPATH("上传favicon保存位置", "uploadLogoSavePath", "favicon", "config_item"),
    AVATAREXT("头像允许后缀", "avatarExt", null, "config_item"),
    BANNERIMAGEEXT("头像允许后缀", "bannerImageExt", null, "config_item"),
    LOGOEXT("网站Logo允许后缀", "logoExt", null, "config_item"),
    FAVICONEXT("网站Favicon允许后缀", "faviconExt", null, "config_item"),
    AVATARMAXSIZE("头像最大上传(M)", "avatarMaxSize", null, "config_item"),
    FAVICONMAXSIZE("网站Favicon最大上传(M)", "faviconMaxSize", null, "config_item"),
    LOGOMAXSIZE("网站Logo最大上传", "logoMaxSize", null, "config_item");
    private final String des;
    private final String item;
    private final String frontedItem;
    private final String key;

    BackendConfigItem(final String des, final String item, String frontedItem, String key) {
        this.des = des;
        this.item = item;
        this.key = key;
        this.frontedItem = frontedItem;
    }
}
