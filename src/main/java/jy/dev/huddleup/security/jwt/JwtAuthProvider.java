package jy.dev.huddleup.security.jwt;

import jy.dev.huddleup.repository.UserRepository;
import jy.dev.huddleup.security.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JwtAuthProvider implements AuthenticationProvider {

    private final JwtDecoder jwtDecoder;
    private final UserRepository userRepository;

    @Autowired
    public JwtAuthProvider(JwtDecoder jwtDecoder, UserRepository userRepository){
        this.jwtDecoder = jwtDecoder;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        Map<String, String> jwtData = jwtDecoder.decode(token);

//        CustomOAuth2User oAuth2User = new CustomOAuth2User(jwtData);
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        return new UsernamePasswordAuthenticationToken(null, null, null);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtPreProcessingToken.class.isAssignableFrom(authentication);
    }
}
