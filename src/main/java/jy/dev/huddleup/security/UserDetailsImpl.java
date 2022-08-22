package jy.dev.huddleup.security;

import jy.dev.huddleup.model.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

import static jy.dev.huddleup.security.jwt.JwtTokenUtils.*;

@Getter
public class UserDetailsImpl implements UserDetails {

    private final String email;
    private final String username;
    private final Long id;

    public UserDetailsImpl(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.id = user.getId();
    }

    public UserDetailsImpl(Map<String, String> jwtData) {
        this.email = jwtData.get(CLAIM_USER_EMAIL);
        this.username = jwtData.get(CLAIM_USER_NICK);
        this.id = Long.parseLong(jwtData.get(CLAIM_USER_ID));
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

}