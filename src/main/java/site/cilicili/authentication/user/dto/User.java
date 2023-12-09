package site.cilicili.authentication.user.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

/**
 * Author:XY
 * PACkAGE:com.example.realworld.domain.user.dto
 * Date:2023/10/25 14:41
 *
 * @author BaiYiChen
 */
@JsonTypeName("User")
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
public class User {
    private String username;
    private String sex;
    private String address;
}
