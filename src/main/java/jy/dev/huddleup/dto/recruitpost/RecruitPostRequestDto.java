package jy.dev.huddleup.dto.recruitpost;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.model.RecruitPostTag;
import jy.dev.huddleup.model.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class RecruitPostRequestDto {

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_TITLE)
    private String title;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_BODY)
    private String body;

    @NotEmpty(message = RecruitPostDtoMsg.EMPTY_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = RecruitPostDtoMsg.INVALID_DATE)
    private String projectStartTime;

    @NotEmpty(message = RecruitPostDtoMsg.EMPTY_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = RecruitPostDtoMsg.INVALID_DATE)
    private String projectEndTime;

    @NotEmpty(message = RecruitPostDtoMsg.EMPTY_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
        message = RecruitPostDtoMsg.INVALID_DATE)
    private String recruitDueTime;

    private String tags;

    private MultipartFile img;

    private Integer requiredDevelopers;

    private Integer requiredDesigners;

    private Integer requiredProjectManagers;

    private String previousImgUrl;

    public LocalDate getProjectStartTime() {
        return getLocalDate(projectStartTime);
    }

    public LocalDate getProjectEndTime() {
        return getLocalDate(projectEndTime);
    }

    public LocalDate getRecruitDueTime() {
        return getLocalDate(recruitDueTime);
    }

    private LocalDate getLocalDate(String date) {
        return Objects.nonNull(date) ?
            LocalDate.parse(date) : null;
    }

    public RecruitPost toEntity(Long userId) {
        return RecruitPost.builder()
            .user(new User(userId))
            .recruitPostTag(getTagIds().stream()
                .map(RecruitPostTag::new)
                .collect(Collectors.toList()))
            .title(getTitle())
            .body(getBody())
            .projectStartTime(getProjectStartTime())
            .projectEndTime(getProjectEndTime())
            .recruitDueTime(getRecruitDueTime())
            .requiredDesigners(getRequiredDesigners())
            .requiredDevelopers(getRequiredDevelopers())
            .requiredProjectManagers(getRequiredProjectManagers())
            .build();
    }

    public List<Long> getTagIds() {
        return Arrays.stream(tags.split(","))
            .map(Long::parseLong)
            .collect(Collectors.toList());
    }
}
