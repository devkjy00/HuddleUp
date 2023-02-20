package jy.dev.huddleup.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.dto.recruitpost.RecruitPostRequestDto;
import jy.dev.huddleup.dto.recruitpost.SimpleRecruitPostResponseDto;
import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.RecruitPost;
import jy.dev.huddleup.model.User;
import jy.dev.huddleup.repository.RecruitPostRepository;
import jy.dev.huddleup.repository.RecruitPostRepositoryCustom;
import jy.dev.huddleup.security.UserDetailsImpl;
import jy.dev.huddleup.util.SortObjProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecruitPostService {

    private final RecruitPostRepository recruitPostRepository;
    private final RecruitPostRepositoryCustom recruitPostRepositoryCustom;
    private final AwsS3Service awsS3Service;

    @Autowired
    public RecruitPostService(RecruitPostRepository recruitPostRepository,
        RecruitPostRepositoryCustom recruitPostRepositoryCustom,
        AwsS3Service awsS3Service) {
        this.recruitPostRepository = recruitPostRepository;
        this.recruitPostRepositoryCustom = recruitPostRepositoryCustom;
        this.awsS3Service = awsS3Service;
    }

    @Transactional
    public RecruitPost createPost(RecruitPostRequestDto requestDto, UserDetailsImpl userDetails) {
        String imgUrl = awsS3Service.uploadFile(requestDto.getImg());
        RecruitPost recruitPost = recruitPostRepository.save(requestDto.toEntity(userDetails.getUserId(), imgUrl));
        requestDto.getTagIds().forEach(recruitPost::addTag);

        return recruitPost;
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    public RecruitPost getPost(Long postId) {
        return recruitPostRepository.findById(postId)
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.POST_NOT_FOUND));
    }

    @Transactional
    public void deletePost(Long postId) {
        RecruitPost recruitPost = recruitPostRepository.findById(postId)
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.POST_NOT_FOUND));

        recruitPostRepository.delete(recruitPost);
    }

    @Transactional
    public void updatePost(Long postId, Long userId, RecruitPostRequestDto dto) {
        RecruitPost post = recruitPostRepository.findByIdAndUser(postId, new User(userId))
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.POST_NOT_FOUND));
        post.update(dto);
    }
}
