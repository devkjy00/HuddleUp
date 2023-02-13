package jy.dev.huddleup.service;

import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.Profile;
import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.UserRepository;
import jy.dev.huddleup.security.oauth2.CustomOAuth2User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public User saveOrUpdate(CustomOAuth2User customOAuth2User) {
        User user = userRepository.findBySocialProviderKey(customOAuth2User.getSocialProviderKey())
            .map(savedUser -> savedUser.update(customOAuth2User))
            .orElse(customOAuth2User.toUser());

        userRepository.save(user);

        user.setProfile(new Profile(user));

        return user;
    }

    @Transactional(readOnly = true)
    public User getUserInfo(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.USER_NOT_FOUND));

    }
}
