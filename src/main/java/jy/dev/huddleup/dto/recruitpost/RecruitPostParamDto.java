package jy.dev.huddleup.dto.recruitpost;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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
    private Integer tagId = 0;

    @Builder
    public RecruitPostParamDto(Integer limit, Integer offSet, Integer sort, Integer tagId){
        this.limit = Objects.nonNull(limit)? limit : this.limit;
        this.offSet = Objects.nonNull(offSet)?
                (offSet) * this.limit : this.offSet;
        this.sort = Objects.nonNull(sort)? sort : this.sort;
        this.tagId = Objects.nonNull(tagId)? tagId : this.tagId;
    }

    public static void validate(@Valid RecruitPostParamDto requestDto){
    }
}
