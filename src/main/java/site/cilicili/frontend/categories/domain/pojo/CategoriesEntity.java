package site.cilicili.frontend.categories.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-02-27 00:01:15
 */

/**
 * (Categories)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:15
 */
@Slf4j
@Getter
@Setter
@Schema(description = "Categories 实体类")
@TableName("categories")
public class CategoriesEntity implements Serializable {
    private static final long serialVersionUID = -65253973903327662L;
    @TableId
    @Schema(description = "ID")
    private Integer id;
    @Schema(description = "学科代码")
    private Integer subjectId;
    @Schema(description = "分类名称")
    private String categoryName;
    @Schema(description = "状态")
    private String status;
}


