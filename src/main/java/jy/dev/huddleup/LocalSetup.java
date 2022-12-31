package jy.dev.huddleup;

import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.UserRepository;
import jy.dev.huddleup.security.jwt.JwtTokenUtils;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import jy.dev.huddleup.util.Social;
import jy.dev.huddleup.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalSetup {

    private final UserRepository userRepository;

    @Autowired
    public LocalSetup(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void setup() {
        User userB = User.builder()
            .email("test@email.com")
            .social(Social.GOOGLE)
            .socialProviderKey("1234")
            .userRole(UserRole.USER)
            .username("username")
            .build();

        User user = userRepository.save(userB);
        printJwt(user);
    }

    public void printJwt(User user) {
        CustomOAuth2User oAuth2User = CustomOAuth2User.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .build();

        String jwt = JwtTokenUtils.generateJwtToken(oAuth2User);
        System.out.println(jwt);
    }
}
