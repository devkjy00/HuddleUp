package jy.dev.huddleup.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import jy.dev.huddleup.exception.DataNotFoundException;
import jy.dev.huddleup.exception.HttpResponse;
import jy.dev.huddleup.model.QRecruitPost;
import jy.dev.huddleup.model.QUser;
import jy.dev.huddleup.model.RecruitPost;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitPostRepositoryCustom {

    private JPAQueryFactory queryFactory;

    private QRecruitPost post = QRecruitPost.recruitPost;
    private QUser user = QUser.user;

    public RecruitPostRepositoryCustom(
        JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public RecruitPost findByIdAndUserId(Long postId, Long userId) {
        return Optional.ofNullable(queryFactory.select(post)
                .from(post)
                .where(post.id.eq(postId),
                    post.user.id.eq(userId))
                .fetchOne())
            .orElseThrow(() -> new DataNotFoundException(HttpResponse.POST_NOT_FOUND));
    }

//    public List<SimpleRecruitPostResponseDto> findAllByTagId(RecruitPostParamDto dto) {
//
//        Map<Long, SimpleRecruitPostResponseDto> dtoMap = new HashMap<>();
//
//        List<Long> postIds = findPostIdsByTags(dto.getTag());
////        List<RecruitPostTagDto> postTagDtos =
////            recruitPostTagRepositoryCustom.findByPostIds(postIds);
//
//        List<SimpleRecruitPostResponseDto> postDtos = queryFactory.select(
//                Projections.fields(SimpleRecruitPostResponseDto.class,
//                    post.id.as("postId"),
//                    user.username,
////                        position.positionName.as("userPosition"),
////                    profile.imageUrl.as("authorImage"),
//                    post.title,
//                    post.projectStartTime,
//                    post.projectEndTime,
//                    post.recruitDueTime
//                ))
//            .from(post)
//            .innerJoin(post.user, user)
////            .innerJoin(profile)
////            .on(profile.user.id.eq(user.id))
//            .where(post.id.in(postIds))
//            .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
//            .fetch();
//
//        postDtos.forEach((post) -> dtoMap.put(post.getPostId(), post));
//
//        postTagDtos.forEach((postTag) -> {
//            SimpleRecruitPostResponseDto postDto = dtoMap.get(postTag.getPostId());
//            postDto.addTag(postTag.getTagId());
//            dtoMap.put(postTag.getPostId(), postDto);
//        });
//
//        return new ArrayList<>(dtoMap.values());
//
//    }
//
//
//    private List<Long> findPostIdsByTags(String tag) {
//        JPAQuery<Long> query = queryFactory
//            .select(post.id)
//            .from(post);
//
//        if (TagValue.notAll(dto.getTagId())) {
//            query.join(post.recruitPostTag, postTag)
//                .on(postTag.tag.id.eq(dto.getTagId()));
//        }
//
//        return query.offset(dto.getOffSet())
//            .limit(dto.getLimit())
//            .orderBy(SortValue.getOrderSpecifier(dto.getSort()))
//            .fetch();
//    }


}
