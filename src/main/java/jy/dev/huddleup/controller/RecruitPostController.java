package jy.dev.huddleup.controller;

import java.util.Map;
import javax.validation.Valid;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import jy.dev.huddleup.dto.recruitpost.SimpleRecruitPostResponseDto;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.security.UserDetailsImpl;
import jy.dev.huddleup.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/main")
    public Map<String, Object> getPosts(
        @RequestParam(value = "limit", required = false, defaultValue = "6") Integer limit,
        @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
        @RequestParam(value = "sort", required = false, defaultValue = "NEW") String sort,
        @RequestParam(value = "tags", required = false, defaultValue = "0") Long tagId) {

        RecruitPostParamDto requestDto = RecruitPostParamDto.builder()
            .limit(limit)
            .offSet(page)
            .sort(sort)
            .tagId(tagId).build();

        RecruitPostParamDto.validate(requestDto);

        return recruitPostService.getPosts(requestDto);

    }

    @GetMapping("/recruitPost/{postId}")
    public SimpleRecruitPostResponseDto getPost(
        @PathVariable Long postId) {
        RecruitPost recruitPost = recruitPostService.getPost(postId);
        return new SimpleRecruitPostResponseDto(recruitPost);
    }

    @DeleteMapping("/recruitPost/{postId}")
    public ResponseEntity<String> deletePost(
        @PathVariable Long postId) {
        recruitPostService.deletePost(postId);

        return HttpResponse.OK.getResponseEntity();
    }


    @PutMapping("/recruitPost/{postId}")
    public ResponseEntity<String> createPost(
        @Valid @ModelAttribute RecruitPostRequestDto requestDto,
        @AuthenticationPrincipal UserDetailsImpl userDetails,
        @PathVariable Long postId) {

        recruitPostService.updatePost(postId, userDetails.getUserId(), requestDto);

        return HttpResponse.OK.getResponseEntity();
    }
}
