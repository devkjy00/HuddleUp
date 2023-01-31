package jy.dev.huddleup.dto;

import jy.dev.huddleup.model.Tag;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagResponseDto {

    private Long tagId;

    private String body;

    public TagResponseDto(Tag tag) {
        this.tagId = tag.getId();
        this.body = tag.getName();
    }
}
