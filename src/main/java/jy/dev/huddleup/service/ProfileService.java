package jy.dev.huddleup.service;

import jy.dev.huddleup.dto.ProfileRequestDto;
import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.Profile;
import jy.dev.huddleup.repository.ProfileRepository;
import jy.dev.huddleup.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Transactional(readOnly = true)
    public Profile findProfile(UserDetailsImpl userDetails) {
        return profileRepository.findById(userDetails.getUserId())
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.PROFILE_NOT_FOUND));
    }

    @Transactional
    public Profile updateProfile(ProfileRequestDto dto, UserDetailsImpl userDetails) {
        Profile profile = findProfile(userDetails);

        return profile.update(dto);
    }


}
