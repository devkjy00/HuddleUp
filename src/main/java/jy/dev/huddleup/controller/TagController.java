package jy.dev.huddleup.controller;

import java.util.List;
import java.util.stream.Collectors;
import jy.dev.huddleup.dto.TagResponseDto;
import jy.dev.huddleup.model.Tag;
import jy.dev.huddleup.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tag")
    public List<TagResponseDto> getTags() {
        List<Tag> tags = tagService.getTags();
        return tags.stream()
            .map(TagResponseDto::new)
            .collect(Collectors.toList());
    }


}
