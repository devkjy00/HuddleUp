package jy.dev.huddleup.dto;

import jy.dev.huddleup.model.Tag;

public class TagResponseDto {

    private Long tagId;

    private String body;

    public TagResponseDto(Tag tag) {
        this.tagId = tag.getId();
        this.body = tag.getName();
    }
}
