package jy.dev.huddleup.controller;

import java.util.Map;
import javax.validation.Valid;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.security.UserDetailsImpl;
import jy.dev.huddleup.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RecruitPostController {

    private final RecruitPostService recruitPostService;

    @Autowired
    public RecruitPostController(
        RecruitPostService recruitPostService) {
        this.recruitPostService = recruitPostService;
    }

    @PostMapping("/recruitPost")
    public ResponseEntity<String> createPost(
        @Valid @ModelAttribute RecruitPostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {

        recruitPostService.createPost(requestDto, userDetails);

        return HttpResponse.OK.getResponseEntity();
    }

//    public ResponseEntity<Map<String, Object>> getPosts(
//            @RequestParam("page") Integer page, @RequestParam("size") Integer size, @RequestParam("sort") String sort, @RequestParam(required = false, value = "tags") Long tagId) {
//
//        Direction sortDirection = Direction.ASC;
//
//        if (sort.equals("new")) {
//            sortDirection = Direction.DESC;
//            sort = "createdAt";
//        }
//        if (sort.equals("due")) sort = "recruitDueTime";
//
//        PageRequest pageRequest = PageRequest.of(page, size, sortDirection, sort);
//        Map<String, Object> body = recruitPostService.getPosts(pageRequest, tagId);
//
//        return ResponseEntity.status(HttpStatus.OK).body(body);
//    }

    @GetMapping("/main")
    public Map<String, Object> getPosts(
        @Nullable @RequestParam("limit") Integer limit,
        @Nullable @RequestParam("page") Integer page,
        @Nullable @RequestParam("sort") Integer sort,
        @Nullable @RequestParam("tag") Integer tagId) {

        RecruitPostParamDto requestDto = RecruitPostParamDto.builder()
            .limit(limit)
            .offSet(page)
            .sort(sort)
            .tagId(tagId).build();

        RecruitPostParamDto.validate(requestDto);

        return recruitPostService.getPosts(requestDto);

    }

}
