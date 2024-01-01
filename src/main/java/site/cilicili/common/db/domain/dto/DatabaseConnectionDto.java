package site.cilicili.common.db.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * (DatabaseConnection)实体类
 *
 * @author ChenBaiYi
 * @since 2023-11-27 18:59:29
 */
@Data
@Builder
@Accessors(chain = true)
public class DatabaseConnectionDto implements Serializable {
    private static final long serialVersionUID = -71532189446371823L;
    List<Long> ids;
    private Integer id;
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
    /**
     * 页
     */
    private Integer pageNum;
    /**
     * 条
     */
    private Integer pageSize;
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 10 : pageSize;
    }
}
