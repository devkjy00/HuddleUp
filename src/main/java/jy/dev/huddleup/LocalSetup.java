package jy.dev.huddleup;

import jy.dev.huddleup.model.Tag;
import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.TagRepository;
import jy.dev.huddleup.security.jwt.JwtTokenUtils;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import jy.dev.huddleup.service.UserService;
import jy.dev.huddleup.util.Social;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalSetup {

    private final UserService userService;
    private final TagRepository tagRepository;

    @Autowired
    public LocalSetup(UserService userService,
        TagRepository tagRepository) {
        this.userService = userService;
        this.tagRepository = tagRepository;
    }

    public void setup() {
        CustomOAuth2User oAuth2User = CustomOAuth2User.builder()
            .email("test@email.com")
            .social(Social.GOOGLE)
            .socialProviderKey("1234")
            .name("username")
            .build();

        User user = userService.saveOrUpdate(oAuth2User);
        printJwt(CustomOAuth2User.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .social(user.getSocial())
            .socialProviderKey(user.getSocialProviderKey())
            .build());

        Tag tagB1 = Tag.builder()
            .Name("name1")
            .build();

        Tag tagB2 = Tag.builder()
            .Name("name2")
            .build();

        tagRepository.save(tagB1);
        tagRepository.save(tagB2);
    }

    public void printJwt(CustomOAuth2User oAuth2User) {
        String jwt = JwtTokenUtils.generateJwtToken(oAuth2User);
    }

    public void printJwt() {
        User user = userService.getUserInfo(1L);
        printJwt(CustomOAuth2User.builder()
            .id(user.getId())
            .name(user.getUsername())
            .email(user.getEmail())
            .social(user.getSocial())
            .socialProviderKey(user.getSocialProviderKey())
            .build());
    }
}
