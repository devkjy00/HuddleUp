package jy.dev.huddleup.dto;

import jy.dev.huddleup.model.Profile;
import jy.dev.huddleup.model.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProfileRequestDto {

    private String phone_number;
    private String residence;
    private String available_period;
    private String available_time;
    private String position;
    private String fields;
    private Boolean face_to_face;
    private String skills;
    private String career_period;
    private String portfolio_url;

    public Profile toEntity(Long userId) {
        return Profile.builder()
            .user(new User(userId))
            .phoneNumber(phone_number)
            .residence(residence)
            .availablePeriod(available_period)
            .availableTime(available_time)
            .position(position)
            .faceToFace(face_to_face)
            .careerPeriod(career_period)
            .portfolioUrl(portfolio_url)
            .build();
    }
}
