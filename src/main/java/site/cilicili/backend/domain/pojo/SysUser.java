package site.cilicili.backend.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

/**
 * (SysUser)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-04 21:48:47
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "SysUser实体类")
public class SysUser implements Serializable {
    private static final long serialVersionUID = 372343052193092864L;
    @Schema(description = "id")
    private Long id;
    private Date createdAt;
    private String createdBy;
    private Date updatedAt;
    private String updatedBy;
    private Date deletedAt;
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
}


