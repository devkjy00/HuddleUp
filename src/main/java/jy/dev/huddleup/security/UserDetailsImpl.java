package jy.dev.huddleup.security;

import jy.dev.huddleup.model.User;
import jy.dev.huddleup.util.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import static jy.dev.huddleup.security.jwt.JwtTokenUtils.*;
@Getter
public class UserDetailsImpl implements UserDetails {

    private Long userId;
    private String username;

    public UserDetailsImpl(User user){
        this.username =  user.getUsername();
        this.userId = user.getId();
    }

    public UserDetailsImpl(Map<String, String> decodedJwt){
        this.username =  decodedJwt.get(CLAIM_USER_EMAIL);
        this.userId = Long.valueOf(decodedJwt.get(CLAIM_USER_ID));
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
        UserRole role = UserRole.USER;

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.name());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }
}

