package jy.dev.huddleup.controller;

import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.service.RecruitPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class RecruitPostController {

    RecruitPostService recruitPostService;

    @Autowired
    public RecruitPostController(
            RecruitPostService recruitPostService){
        this.recruitPostService = recruitPostService;
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
            @Nullable @RequestParam("tag") Integer tagId){

        RecruitPostParamDto requestDto = RecruitPostParamDto.builder()
                .limit(limit)
                .offSet(page)
                .sort(sort)
                .tagId(tagId).build();

        RecruitPostParamDto.validate(requestDto);

        return recruitPostService.getPosts(requestDto);

    }

}
