package jy.dev.huddleup.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    String test(){
        log.info("logging test");
        return "test";
    }

    @GetMapping("/")
    String dummy(){
        log.info("logging test");
        return "test";
    }
}
