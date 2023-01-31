package jy.dev.huddleup.service;

import java.util.List;
import jy.dev.huddleup.model.Tag;
import jy.dev.huddleup.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getTags() {
        return tagRepository.findAll();
    }
}
