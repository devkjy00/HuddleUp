package jy.dev.huddleup.security.oauth2;

import jy.dev.huddleup.security.jwt.JwtTokenUtils;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Value("${spring.frontend.url}")
    private String FRONTEND_URL;

	private JwtTokenUtils jwtTokenUtils;

	@Autowired
	CustomAuthenticationSuccessHandler(JwtTokenUtils jwtTokenUtils){
		this.jwtTokenUtils = jwtTokenUtils;
	}


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

        String jwt = jwtTokenUtils.generateJwtToken((CustomOAuth2User) authentication.getPrincipal());

        String url = makeRedirectUrl(jwt);

//        jwtProvider.setJwtToHeader(jwt, response);
//
//        getRedirectStrategy().sendRedirect(request, response, url);
    }

    private String makeRedirectUrl(String jwt) {

        return UriComponentsBuilder
                .fromUriString(FRONTEND_URL + "/login/callback?" + "jwt=" + jwt)
                .build()
                .toUriString();
    }
}
