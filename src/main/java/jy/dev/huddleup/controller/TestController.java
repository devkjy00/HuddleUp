package jy.dev.huddleup.controller;

import jy.dev.huddleup.model.Tag;
import jy.dev.huddleup.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {

    private final TagRepository repository;


    @GetMapping("/test")
    void test() {
        Tag tag = repository.find(1L).orElseThrow();

        Long id = tag.getRecruitPostTag().get(0).getRecruitPost().getId();

        System.out.println("tag");

    }

}
