package site.cilicili.common.db.domain.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * (DatabaseConnection)实体类
 *
 * @author ChenBaiYi
 * @since 2023-12-06 09:24:05
 */
@Slf4j
@Data
@Schema(description = "DatabaseConnection实体类")
@AllArgsConstructor
public class DatabaseConnection implements Serializable {
    private static final long serialVersionUID = -13143046785078850L;
    private Integer id;
    private String url;
    private String username;
    private String password;
    private String driver;
    private String scheme;
}


