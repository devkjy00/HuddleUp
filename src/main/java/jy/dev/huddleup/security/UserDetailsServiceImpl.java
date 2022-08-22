package jy.dev.huddleup.security;

import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException(HttpResponse.USER_NOT_FOUND.toString()));

        return new UserDetailsImpl(user);
    }
}
