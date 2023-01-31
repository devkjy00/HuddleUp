package jy.dev.huddleup.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import jy.dev.huddleup.dto.recruitpost.RecruitPostParamDto;
import jy.dev.huddleup.model.QRecruitPost;
import jy.dev.huddleup.model.QRecruitPostTag;
import jy.dev.huddleup.model.QTag;
import jy.dev.huddleup.model.QUser;
import jy.dev.huddleup.util.SortObjProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitPostRepositoryCustom {

    private JPAQueryFactory queryFactory;

    private QRecruitPost qPost = QRecruitPost.recruitPost;
    private QUser qUser = QUser.user;
    private QRecruitPostTag qPostTag = QRecruitPostTag.recruitPostTag;
    private QTag qTag = QTag.tag;

    public RecruitPostRepositoryCustom(
        JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public Page<Long> findPostIdsByTagId(RecruitPostParamDto dto) {

        List<Long> content = setTagQuery(dto).offset(dto.getOffSet())
            .limit(dto.getLimit())
            .orderBy(SortObjProvider.valueOf(dto.getSort()).getOrderSpecifier())
            .fetch();

        int totalSize = setTagQuery(dto).fetch().size();

        return new PageImpl<>(content, dto.getPageable(), totalSize);
    }

    private JPAQuery<Long> setTagQuery(RecruitPostParamDto dto) {
        final Long ALL = 0L;

        JPAQuery<Long> query = queryFactory
            .select(qPost.id)
            .from(qPost);

        if (!Objects.equals(dto.getTagId(), ALL)) {
            query.innerJoin(qPost.recruitPostTags, qPostTag)
                .on(qPostTag.tag.id.eq(dto.getTagId()));
        }

        return query;
    }

}
