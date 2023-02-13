package jy.dev.huddleup.dto;

import jy.dev.huddleup.model.Profile;
import jy.dev.huddleup.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserResponseDto {

    private String username;
    private String email;
    private String phoneNumber;
    private String profileImageUrl;
    private String residence;
    private String availablePeriod;
    private String availableTime;
    private String position;
    private String fields;
    private Boolean faceToFace;
    private String skills;
    private String careerPeriod;
    private String portfolioUrl;

    public UserResponseDto(User user) {

        Profile profile = user.getProfile();

        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phoneNumber = profile.getPhoneNumber();
        this.profileImageUrl = profile.getImageUrl();
        this.residence = profile.getResidence();
        this.availablePeriod = profile.getAvailablePeriod();
        this.availableTime = profile.getAvailableTime();
        this.position = profile.getPosition();
        this.fields = profile.getJobGroup();
        this.faceToFace = profile.getFaceToFace();
        this.skills = profile.getSkills();
        this.careerPeriod = profile.getCareerPeriod();
        this.portfolioUrl = profile.getPortfolioUrl();
    }
}
