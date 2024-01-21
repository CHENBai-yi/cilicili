package site.cilicili.backend.log.domain.pojo;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;
import java.util.Optional;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-01-17 21:11:42
 */

/**
 * (SysLogLogin)实体类
 *
 * @author ChenBaiYi
 * @since 2024-01-17 21:11:42
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysLogLogin 实体类")
@TableName("sys_log_login")
public class SysLogLoginEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -47347874418528182L;
    @Schema(description = "排序")
    private Long sort;
    @Schema(description = "系统内置")
    private String stable;
    @Schema(description = "状态")
    private String status;
    @Schema(description = "备注描述")
    private String memo;
    @Schema(description = "登录用户名")
    private String loginUsername;
    @Schema(description = "登录IP")
    private String loginIp;
    @Schema(description = "登录浏览器")
    private String loginBrowser;
    @Schema(description = "登录操作系统")
    private String loginOs;
    @Schema(description = "登录平台")
    private String loginPlatform;
    private String loginSuccess;
    @Schema(description = "在线时长")
    @TableField(exist = false)
    private String duration;
    @Schema(description = "用户名")
    @TableField(exist = false)
    private String username;

    public String getDuration() {
        final DateTime beginDate = DateUtil.date(this.getCreatedAt());
        final DateTime endDate = DateUtil.date(this.getUpdatedAt());
        return Optional.of(ObjUtil.isAllNotEmpty(endDate, beginDate))
                .filter(f -> f)
                .map(f -> {
                    String interval = null;
                    if (DateUtil.between(beginDate, endDate, DateUnit.WEEK, true) >= 1) {
                        interval = String.format("%1$s周前", DateUtil.between(beginDate, endDate, DateUnit.WEEK, true));
                    } else if (DateUtil.between(beginDate, endDate, DateUnit.DAY, true) >= 1) {
                        interval = String.format("%1$s天前", DateUtil.between(beginDate, endDate, DateUnit.DAY, true));
                    } else if (DateUtil.between(beginDate, endDate, DateUnit.HOUR, true) >= 1) {
                        interval = String.format("%1$s小时前", DateUtil.between(beginDate, endDate, DateUnit.HOUR, true));
                    } else if (DateUtil.between(beginDate, endDate, DateUnit.MINUTE, true) >= 1) {
                        interval = String.format("%1$s分钟前", DateUtil.between(beginDate, endDate, DateUnit.MINUTE, true));
                    } else if (DateUtil.between(beginDate, endDate, DateUnit.SECOND, true) >= 1) {
                        interval = String.format("%1$s秒前", DateUtil.between(beginDate, endDate, DateUnit.SECOND, true));
                    }
                    return interval;
                }).orElse("");

    }
}


