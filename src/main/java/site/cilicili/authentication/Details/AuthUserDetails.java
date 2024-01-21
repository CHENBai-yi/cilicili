package site.cilicili.authentication.Details;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author BaiYiChen
 */
public class AuthUserDetails implements UserDetails {
    private final Long id;
    private final String username;
    private final String roleCode;
    private final String realName;

    @Builder
    public AuthUserDetails(Long id, String username, String roleCode, final String realName) {
        this.id = id;
        this.username = username;
        this.roleCode = roleCode;
        this.realName = realName;
    }

    public String getRealName() {
        return realName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public Long getId() {
        return id;
    }

    public String getusername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // no authority in this project
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
