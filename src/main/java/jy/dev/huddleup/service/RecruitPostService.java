package jy.dev.huddleup.service;

import java.util.Map;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.repository.RecruitPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository) {
        this.recruitPostRepository = recruitPostRepository;
    }

    public Map<String, Object> getPosts(RecruitPostParamDto requestDto){

    }
}
