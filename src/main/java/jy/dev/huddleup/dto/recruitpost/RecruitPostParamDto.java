package jy.dev.huddleup.dto.recruitpost;

import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.util.SortObjProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class RecruitPostParamDto {

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    @Min(value = 0, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    @Max(value = 20, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    private Integer limit;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    @Min(value = 0, message = RecruitPostDtoMsg.INVALID_PARAMETER)
    private Integer offSet;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    private String sort;

    private SortObjProvider sortObj;

    @NotBlank(message = RecruitPostDtoMsg.EMPTY_PARAMETER)
    private Long tagId;

    @Builder
    public RecruitPostParamDto(Integer limit, Integer offSet, String sort, Long tagId) {
        this.limit = Optional.ofNullable(limit).orElse(this.limit);
        this.offSet = Optional.ofNullable(offSet).orElse(this.offSet);
        this.sort = Optional.of(sort.toUpperCase()).orElse(this.sort);
        this.tagId = Optional.ofNullable(tagId).orElse(this.tagId);
    }

    public Pageable getPageable() {
        return PageRequest.of(this.offSet, this.limit);
    }


    public static void validate(@Valid RecruitPostParamDto requestDto) {
        try {
            SortObjProvider sortObj = SortObjProvider.valueOf(requestDto.getSort());
            requestDto.setSortObj(sortObj);

        } catch (IllegalArgumentException e) {
            throw new DataNotFoundException(HttpResponse.INVALID_INPUT);
        }
    }
}
