package site.cilicili.authentication.user.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author:XY
 * PACkAGE:com.example.realworld.domain.user.dto
 * Date:2023/10/25 14:41
 *
 * @author BaiYiChen
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDtoTest extends User {
    private String[] hobbies;
    private Schoole Schoole;

    @JsonTypeName("User")
    @Data
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonRootName(value = "shcoll_info")
    public static class Schoole {
        private String schoolName;
        private String schoolAddress;
    }
}