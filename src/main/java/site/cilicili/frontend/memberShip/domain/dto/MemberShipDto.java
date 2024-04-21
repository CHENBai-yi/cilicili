package site.cilicili.frontend.memberShip.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-13 20:20:56
 */

/**
 * (MemberShip)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:56
 */
@Slf4j
@Data
@Builder
@Accessors(chain = true)
@Schema(description = "MemberShip数据传输类")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberShipDto implements Serializable {
    private static final long serialVersionUID = 399862768936488873L;

    @JsonIgnore
    protected Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;

    @JsonIgnore
    protected String createdBy;

    @JsonIgnore
    protected LocalDateTime updatedAt;

    @JsonIgnore
    protected String updatedBy;

    @JsonIgnore
    @Schema(description = "会员id")
    private Long userId;

    @JsonIgnore
    @Schema(description = "会员名")
    private String username;

    private String email;

    @JsonIgnore
    private String status;

    @JsonIgnore
    private Integer logicalDelete;

    private String tradeNo;
    private String product;
    private String method;
    private BigDecimal price;
    private BigDecimal discount;
}
