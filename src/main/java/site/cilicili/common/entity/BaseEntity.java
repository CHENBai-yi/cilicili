package site.cilicili.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author BaiYiChen
 */
@Getter
@Setter
public class BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    protected Integer delete;
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;
    @TableField(fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

    @TableLogic
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime deletedAt;
}
