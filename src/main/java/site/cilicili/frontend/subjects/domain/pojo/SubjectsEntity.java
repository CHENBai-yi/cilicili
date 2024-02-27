package site.cilicili.frontend.subjects.domain.pojo;

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
 * @since 2024-02-27 00:01:39
 */

/**
 * (Subjects)实体类
 *
 * @author ChenBaiYi
 * @since 2024-02-27 00:01:39
 */
@Slf4j
@Getter
@Setter
@Schema(description = "Subjects 实体类")
@TableName("subjects")
public class SubjectsEntity implements Serializable {
    private static final long serialVersionUID = 693524493514829702L;
    @TableId
    @Schema(description = "ID")
    private Integer id;
    @Schema(description = "学科代码")
    private String subjectCode;
    @Schema(description = "学科名称")
    private String subjectName;
    @Schema(description = "排序")
    private Integer sort;
    @Schema(description = "学科描述")
    private String memo;
}


