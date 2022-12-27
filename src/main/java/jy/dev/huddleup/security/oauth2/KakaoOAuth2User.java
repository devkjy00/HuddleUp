package jy.dev.huddleup.security.oauth2;

import java.util.List;
import jy.dev.huddleup.util.Social;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoOAuth2User extends CustomOAuth2User {
    public static final String ATTRIBUTES = "kakao_account";
    public static final String ATTRIBUTES_PROFILE = "profile";
    public static final String ATTRIBUTES_NAME = "nickname";
    public static final String ATTRIBUTES_EMAIL = "email";
    public static final String ATTRIBUTES_KEY = "id";


    public KakaoOAuth2User(Map<String, Object> providedAttributes) {

        try {
            super.attributes = (Map<String, Object>) providedAttributes.get(ATTRIBUTES);

            if (!providedAttributes.containsKey(ATTRIBUTES_KEY) ||
                    !attributes.keySet().containsAll(List.of(ATTRIBUTES_PROFILE, ATTRIBUTES_EMAIL))) {
//                throw new InvalidOAuth2AttributesException();
                throw new IllegalArgumentException();
            }

            super.name = String.valueOf(((Map<?,?>) attributes.get(ATTRIBUTES_PROFILE)).get(ATTRIBUTES_NAME));
            super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
            super.socialProviderKey = String.valueOf(providedAttributes.get(ATTRIBUTES_KEY));
            super.social = Social.KAKAO;
        } catch (NullPointerException nullPointerException) {
//            throw new InvalidOAuth2AttributesException(nullPointerException);
            throw new IllegalArgumentException();
        }
    }
}
