package jy.dev.huddleup.service;

import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.repository.RecruitPostRepository;
import jy.dev.huddleup.repository.RecruitPostRepositoryCustom;
import jy.dev.huddleup.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

//    public Map<String, Object> getPosts(RecruitPostParamDto requestDto) {
//
//    }
}
