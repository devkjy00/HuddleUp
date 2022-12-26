package jy.dev.huddleup.security.oauth2;

import jy.dev.huddleup.security.jwt.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Value("${spring.frontend.url}")
    private String FRONTEND_URL;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {

		log.info("social login success");

        String jwt = JwtTokenUtils.generateJwtToken((CustomOAuth2User) authentication.getPrincipal());

        String url = makeRedirectUrl(jwt);

//        setJwtToHeader(jwt, response);

        getRedirectStrategy().sendRedirect(request, response, url);

        log.info(url);

    }

    private String makeRedirectUrl(String jwt) {

        return UriComponentsBuilder
                .fromUriString(FRONTEND_URL + "/login/callback?" + "jwt=" + jwt)
                .build()
                .toUriString();
    }

//    public void setJwtToHeader(String jwt, HttpServletResponse response) {
//        response.addHeader("Authorization", jwt);
//        response.addHeader("Token-type", "Bearer");
//    }
}
