package site.cilicili.backend.user.domain.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.entity.BaseEntity;

import java.io.Serializable;

/**
 * (SysUser)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-14 21:53:22
 */
@Slf4j
@Getter
@Setter
@Schema(description = "SysUser 实体类")
@TableName("sys_user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysUserEntity extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -18979173431235042L;

    @TableLogic(value = "1", delval = "0")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    protected Integer logicalDelete;

    @Schema(description = "排序")
    private Long sort;

    @Schema(description = "系统内置")
    @JacksonInject("stable")
    private String stable;

    @Schema(description = "状态")
    @JacksonInject("status")
    private String status;

    @Schema(description = "备注描述")
    private String memo;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "真实姓名")
    private String realName;

    @Schema(description = "用户密码")
    @TableField(fill = FieldFill.INSERT)
    @JsonIgnore
    private String password;

    @Schema(description = "头像")
    private String avatar;

    @Schema(description = "性别")
    private String gender;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    @Schema(description = "手机号")
    private String mobile;

    @Schema(description = "邮箱")
    private String email;
}
