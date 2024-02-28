package site.cilicili.authentication.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.cilicili.common.entity.BaseEntity;

/**
 * @author BaiYiChen
 */
@Getter
@Setter
@NoArgsConstructor
@TableName("sys_user")
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 372343052193092864L;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    private String stable;

    @Schema(description = "状态")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "用户名")
    private String username;

    @TableField(exist = false)
    @Schema(description = "角色编码")
    private String roleCode;

    @TableField(exist = false)
    @Schema(description = "登录token")
    private String token;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "用户密码")
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;

    @Builder
    public UserEntity(
            final Long sort,
            final String stable,
            final String status,
            final String memo,
            final String username,
            final String nickname,
            final String realName,
            final String password,
            final String avatar,
            final String gender,
            final String mobile,
            final String email) {
        this.sort = sort;
        this.stable = stable;
        this.status = status;
        this.memo = memo;
        this.username = username;
        this.nickname = nickname;
        this.realName = realName;
        this.password = password;
        this.avatar = avatar;
        this.gender = gender;
        this.mobile = mobile;
        this.email = email;
    }
}
