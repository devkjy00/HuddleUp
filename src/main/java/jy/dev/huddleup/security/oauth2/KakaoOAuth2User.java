package jy.dev.huddleup.security.oauth2;

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

            providedAttributes.forEach((key, value) -> log.info(key + " : " + value));

//            ((Map<?,?>) providedAttributes.get(ATTRIBUTES)).forEach((key, value) -> log.info(key + " : " + value));

//            if (!providedAttributes.containsKey(ATTRIBUTES_KEY) ||
//                    !attributes.keySet().containsAll(List.of(ATTRIBUTES_NAME, ATTRIBUTES_EMAIL))) {
//                throw new InvalidOAuth2AttributesException();
//            }

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
