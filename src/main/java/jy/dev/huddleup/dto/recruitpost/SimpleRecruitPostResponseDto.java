package jy.dev.huddleup.dto.recruitpost;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import jy.dev.huddleup.dto.TagResponseDto;
import jy.dev.huddleup.model.Profile;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.model.RecruitPostTag;
import jy.dev.huddleup.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleRecruitPostResponseDto {

    private Long postId;

    private String username;

//    private String introduce;

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

    private List<TagResponseDto> tags;

    public SimpleRecruitPostResponseDto(RecruitPost recruitPost) {
        User user = recruitPost.getUser();
        Profile profile = user.getProfile();

        this.username = user.getUsername();
        this.userPosition = profile.getPosition();
        this.authorImage = profile.getImageUrl();
        this.tags = recruitPost.getRecruitPostTag().stream()
            .map(RecruitPostTag::getTag)
            .map(TagResponseDto::new)
            .collect(Collectors.toList());

        this.postId = recruitPost.getId();
        this.title = recruitPost.getTitle();
        this.projectImage = recruitPost.getImageUrl();
        this.requiredDevelopers = recruitPost.getRequiredDevelopers();
        this.requiredDesigners = recruitPost.getRequiredDesigners();
        this.requiredProjectManagers = recruitPost.getRequiredProjectManagers();
        this.projectStartTime = recruitPost.getProjectStartTime();
        this.recruitDueTime = recruitPost.getRecruitDueTime();
    }


}
