package jy.dev.huddleup.security.oauth2;

import java.util.List;
import java.util.Map;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.util.Social;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GoogleOAuth2User extends CustomOAuth2User {

    public static final String ATTRIBUTES_NAME = "name";
    public static final String ATTRIBUTES_EMAIL = "email";
    public static final String ATTRIBUTES_KEY = "sub";

    public GoogleOAuth2User(Map<String, Object> attributes){

        try {
            if (!attributes.containsKey(ATTRIBUTES_KEY) ||
                    !attributes.keySet().containsAll(List.of(ATTRIBUTES_NAME, ATTRIBUTES_EMAIL))) {
                throw new IllegalArgumentException();
            }

            super.name = String.valueOf(attributes.get(ATTRIBUTES_NAME));
            super.email = String.valueOf(attributes.get(ATTRIBUTES_EMAIL));
            super.socialProviderKey = String.valueOf(attributes.get(ATTRIBUTES_KEY));
            super.social = Social.GOOGLE;
        } catch (NullPointerException | IllegalArgumentException e) {
            throw new IllegalArgumentException(HttpResponse.INVALID_GOOGLE_OAUTH.getMessage());
        }
    }
}
