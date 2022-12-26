package jy.dev.huddleup.security.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import jy.dev.huddleup.exception.HttpResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper mapper;

    public CustomAuthenticationFailureHandler() {
        this.mapper = new ObjectMapper();
    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

		log.info("social login fail");

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        ResponseEntity<String> responseEntity =
                HttpResponse.INVALID_ID_PASSWORD.getResponseEntity();

        String message = mapper.writeValueAsString(responseEntity);

        response.getWriter().write(message);
    }

}
