package jy.dev.huddleup.dto.recruitpost;

import java.time.LocalDate;

public class SimpleRecruitPostResponseDto {

    private Long postId;

    private String username;

    private String introduce;

    private String userPosition;

    private String authorImage;

    private String title;

    private String projectImage;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private LocalDate projectStartTime;

    private LocalDate projectEndTime;

    private LocalDate recruitDueTime;

//    private List<TagResponseDto> tags = new ArrayList<>();

//    private List<TagResponseDto> authorFields;
}
