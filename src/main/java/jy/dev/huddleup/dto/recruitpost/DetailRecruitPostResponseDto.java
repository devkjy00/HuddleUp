package jy.dev.huddleup.dto.recruitpost;

import java.time.LocalDate;

public class DetailRecruitPostResponseDto {

    private Long userId;

    private Long postId;

    private String title;

    private String body;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private String imageUrl;

    private Boolean recruitStatus;

//    private List<TagResponseDto> tags;
//
//    private List<ApplicantResponse> applicants;
}
