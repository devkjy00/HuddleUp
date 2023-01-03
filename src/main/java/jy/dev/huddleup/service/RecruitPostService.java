package jy.dev.huddleup.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import jy.dev.huddleup.dto.recruitpost.SimpleRecruitPostResponseDto;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.repository.RecruitPostRepository;
import jy.dev.huddleup.repository.RecruitPostRepositoryCustom;
import jy.dev.huddleup.security.UserDetailsImpl;
import jy.dev.huddleup.util.SortObjProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;
    private final RecruitPostRepositoryCustom recruitPostRepositoryCustom;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository,
        RecruitPostRepositoryCustom recruitPostRepositoryCustom) {
        this.recruitPostRepository = recruitPostRepository;
        this.recruitPostRepositoryCustom = recruitPostRepositoryCustom;
    }

    public RecruitPost createPost(RecruitPostRequestDto requestDto, UserDetailsImpl userDetails) {
        return recruitPostRepository.save(requestDto.toEntity(userDetails.getUserId()));
    }

    public Map<String, Object> getPosts(RecruitPostParamDto requestDto) {
        Map<String, Object> result = new HashMap<>();

        Page<Long> postIds = recruitPostRepositoryCustom.findPostIdsByTagId(requestDto);
        SortObjProvider sortObj = requestDto.getSortObj();

        List<RecruitPost> recruitPosts = recruitPostRepository.findAllByIdIn(postIds.getContent(), sortObj.getSort());

        result.put("isLast", postIds.isLast());
        result.put("content", recruitPosts.stream()
            .map(SimpleRecruitPostResponseDto::new)
            .collect(Collectors.toList()));

        return result;
    }
}
