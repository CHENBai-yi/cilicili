package site.cilicili.authentication.user.dto;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

/**
 * @author BaiYiChen
 */
@Getter
@AllArgsConstructor
@Builder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class UserDto {
    private String email;
    private String token;
    private String username;

    @JsonProperty("real_name")
    private String realName;

    private String nickname;
    private String avatar;
    private String gender;
    private String mobile;
    @JsonProperty("userButton")
    private List<String> userButton;

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
    @Setter
    @NoArgsConstructor
    @JsonTypeName("user")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NONE, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    public static class Login {
        private String username;

        @NotNull
        @Size(min = 8, max = 32)
        private String password;

        private String email;
        private String code;
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

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class FrontendRegistration {
        private String username;

        @NotNull
        @Email
        private String email;

        @NotBlank
        @Size(min = 8, max = 32)
        private String password;

        @NotBlank
        @Size(min = 6, max = 6)
        private String code;

        private String realName;

        @NotBlank
        @JacksonInject("roleCode")
        private String roleCode;

        @Schema(description = "头像")
        @JacksonInject("avatar")
        private String avatar;
    }

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class GetEmailCode {
        @NotNull
        @Email
        private String email;

        private Boolean login;
    }
}
