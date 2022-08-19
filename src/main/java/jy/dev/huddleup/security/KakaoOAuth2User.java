package jy.dev.huddleup.security;

import jy.dev.huddleup.util.Social;

import java.util.Map;

public class KakaoOAuth2User extends CustomOAuth2User {
    public static final String ATTRIBUTES = "properties";
    public static final String ATTRIBUTES_NAME = "profile_nickname";
    public static final String ATTRIBUTES_EMAIL = "account_email";
    public static final String ATTRIBUTES_KEY = "id";


    public KakaoOAuth2User(Map<String, Object> providedAttributes) {
        try {
            super.attributes = (Map<String, Object>) providedAttributes.get(ATTRIBUTES);

//            if (!providedAttributes.containsKey(ATTRIBUTES_KEY) ||
//                    !attributes.keySet().containsAll(List.of(ATTRIBUTES_NAME, ATTRIBUTES_EMAIL, ATTRIBUTES_PICTURE))) {
//                throw new InvalidOAuth2AttributesException();
//            }

            super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
            super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
            super.socialProviderKey = String.valueOf(providedAttributes.get(ATTRIBUTES_KEY));
            super.social = Social.KAKAO;
        } catch (NullPointerException nullPointerException) {
//            throw new InvalidOAuth2AttributesException(nullPointerException);
            throw new IllegalArgumentException();
        }
    }
}
