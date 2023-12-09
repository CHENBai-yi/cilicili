package site.cilicili.authentication.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author BaiYiChen
 */
@Getter
@AllArgsConstructor
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class UserDto {
    private String email;
    private String token;
    private String username;
    @JsonProperty("real_name")
    private String realName;
    private String nickname;
    private String avatar;

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Registration {
        @NotNull
        @Pattern(regexp = "[\\w\\d]{1,30}", message = "string contains alphabet or digit with length 1 to 30")
        private String username;

        @NotNull
        @Email
        private String email;

        @NotBlank
        @Size(min = 8, max = 32)
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @JsonTypeName("user")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Login {
        @NotNull
        private String username;

        @NotBlank
        @Size(min = 8, max = 32)
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @JsonTypeName("user")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Update {
        private Long id;
        private String email;
        private String username;
        private String bio;
        private String image;
        private String password;
    }
}