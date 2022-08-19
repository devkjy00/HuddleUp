package jy.dev.huddleup.security;

import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.UserRepository;
import jy.dev.huddleup.util.Social;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> defaultOAuth2UserService = new DefaultOAuth2UserService();


        // 플랫폼 이름
        String OAuthProvider = userRequest.getClientRegistration().getRegistrationId();
        Social social = Social.valueOf(OAuthProvider.toUpperCase());

        // access token으로 유저 데이터 가져오기
        OAuth2User defaultOAuth2User = defaultOAuth2UserService.loadUser(userRequest);
        CustomOAuth2User customOAuth2User = social.getOAuth2User().apply(defaultOAuth2User.getAttributes());

        saveOrUpdate(customOAuth2User);

        return customOAuth2User;
    }


    public void saveOrUpdate(CustomOAuth2User customOAuth2User){
        User user = userRepository.findBySocialProviderKey(customOAuth2User.getSocialProviderKey())
                .map(savedUser -> savedUser.update(customOAuth2User))
                .orElse(customOAuth2User.toUser());

        userRepository.save(user);
    }
}
