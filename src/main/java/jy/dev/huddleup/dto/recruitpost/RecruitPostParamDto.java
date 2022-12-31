package jy.dev.huddleup.dto.recruitpost;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitPostParamDto {

    private final String ALL = "all";

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    @Min(value = 0, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    @Max(value = 20, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    private Integer limit = 6;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    @Min(value = 0, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    private Integer offSet = 0;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    @Min(value = 0, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    @Max(value = 1, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    private Integer sort = 0;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    private String tag = "all";

    @Builder
    public RecruitPostParamDto(Integer limit, Integer offSet, Integer sort, String tag) {
        this.limit = Optional.ofNullable(limit).orElse(this.limit);
        this.offSet = Optional.ofNullable(offSet).orElse(this.offSet);
        this.sort = Optional.ofNullable(sort).orElse(this.sort);
        this.tag = Optional.ofNullable(tag).orElse(this.tag);
    }

    public static void validate(@Valid RecruitPostParamDto requestDto) {
    }
}
