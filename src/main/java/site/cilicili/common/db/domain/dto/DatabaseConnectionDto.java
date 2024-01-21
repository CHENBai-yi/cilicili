package site.cilicili.common.db.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (DatabaseConnection)实体类
 *
 * @author ChenBaiYi
 * @since 2023-11-27 18:59:29
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DatabaseConnectionDto implements Serializable {
    private static final long serialVersionUID = -71532189446371823L;
    private String url;

    @JsonAlias({"db_user"})
    private String dbUser;

    @JsonAlias({"db_password"})
    private String dbPassword;

    private String driver;
    private String scheme;

    @JsonAlias({"db_schema"})
    private String dbSchema;

    @JsonAlias({"db_port"})
    private String dbPort;

    @JsonAlias({"db_host"})
    private String dbHost;

    @JsonAlias({"db_type"})
    private String dbType;
}
