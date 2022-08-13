package jy.dev.huddleup.dto.recruitpost;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class RecruitPostRequestDto {
    @NotBlank(message = RecruitPostDtoMsg.EMPTY_TITLE)
    private String title;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_BODY)
    private String body;

    @NotEmpty(message = RecruitPostDtoMsg.INVALID_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
            message = RecruitPostDtoMsg.INVALID_DATE)
    private String projectStartTime;

    @NotEmpty(message = RecruitPostDtoMsg.INVALID_DATE)
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$",
            message = RecruitPostDtoMsg.INVALID_DATE)
    private String projectEndTime;

    @NotEmpty(message = RecruitPostDtoMsg.INVALID_DATE)
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

    private LocalDate getLocalDate(String date){
        return Objects.nonNull(date)?
                LocalDate.parse(date) : null;
    }


}
