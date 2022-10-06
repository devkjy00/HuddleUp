package jy.dev.huddleup.util;

import jy.dev.huddleup.security.oauth2.GoogleOAuth2User;
import jy.dev.huddleup.security.oauth2.KakaoOAuth2User;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import lombok.Getter;

import java.util.Map;
import java.util.function.Function;

@Getter
public enum Social {
    KAKAO(attribute -> new KakaoOAuth2User(attribute))
    ,GOOGLE(attribute -> new GoogleOAuth2User(attribute));

    private Function<Map<String, Object>, CustomOAuth2User> oAuth2User;

    Social(Function<Map<String, Object>, CustomOAuth2User> oAuth2User) {
        this.oAuth2User= oAuth2User;
    }
}
