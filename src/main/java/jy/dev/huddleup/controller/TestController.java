package jy.dev.huddleup.controller;

import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    String test() {
        throw new DataNotFoundException(HttpResponse.POST_NOT_FOUND);
    }

    @GetMapping("/")
    String dummy() {
        log.info("logging test");
        return "test";
    }
}
