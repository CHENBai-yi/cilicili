package site.cilicili.frontend.memberShip.domain.pojo;

import cn.hutool.core.date.BetweenFormatter;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import site.cilicili.common.constant.pay.AliPayStatus;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

/**
 * PACkAGE: D:/Documents/JavaCode/Code/cilicili(嗤哩嗤哩)
 *
 * @author ChenBaiYi
 * EMAIL: chenbaiyi.work@gmail.com
 * @since 2024-04-13 20:20:54
 */

/**
 * (MemberShip)实体类
 *
 * @author ChenBaiYi
 * @since 2024-04-13 20:20:54
 */
@Slf4j
@Getter
@Setter
@Schema(description = "MemberShip 实体类")
@TableName("member_ship")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MemberShipEntity implements Serializable {
    private static final long serialVersionUID = 344637209176291202L;

    @JsonIdentityReference(alwaysAsId = true)
    @TableId(type = IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    @TableField(fill = FieldFill.INSERT)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT)
    protected String createdBy;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    protected LocalDateTime updatedAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    protected String updatedBy;
    @Schema(description = "会员id")
    @JsonProperty("uId")
    private Long userId;

    @Schema(description = "会员名")
    private String username;

    private String email;
    private String status;
    private Integer logicalDelete;
    private String tradeNo;
    private String product;
    private String method;
    private BigDecimal price;
    @TableField(exist = false)
    private String days;
    @TableField(exist = false)
    private String type = "实名认证资格购买";
    @TableField(exist = false)
    private BigDecimal discount;

    @JsonGetter("days")
    public String getDaysJson() {
        return DateUtil.formatBetween(Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant()), DateUtil.date(), BetweenFormatter.Level.DAY);
    }

    public String getStatus() {
        if (AliPayStatus.SUCCESS.getStatus().equals(status)) {
            return "交易成功";
        } else if (AliPayStatus.FINISHED.getStatus().equals(status)) {
            return "交易完成";
        } else {
            return "已付款";
        }
    }

    public BigDecimal getPrice() {
        return NumberUtil.round(Optional.ofNullable(price).orElse(BigDecimal.ZERO), 2);
    }

    public BigDecimal getDiscount() {
        return NumberUtil.round(Optional.ofNullable(discount).orElse(BigDecimal.ZERO), 2);
    }
}
