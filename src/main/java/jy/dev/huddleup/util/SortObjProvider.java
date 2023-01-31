package jy.dev.huddleup.util;

import static jy.dev.huddleup.model.QRecruitPost.recruitPost;

import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public enum SortObjProvider {
    NEW(Sort.Direction.ASC, "createdAt", recruitPost.createdAt.asc()),
    DUE(Sort.Direction.DESC, "recruitDueTime", recruitPost.recruitDueTime.desc());

    private Sort.Direction direction;

    private String sortBy;

    private OrderSpecifier<?> orderSpecifier;

    SortObjProvider(Sort.Direction direction, String sortBy, OrderSpecifier<?> orderSpecifier) {
        this.direction = direction;
        this.sortBy = sortBy;
        this.orderSpecifier = orderSpecifier;
    }

    public Sort getSort() {
        return Sort.by(direction, sortBy);
    }

    public static OrderSpecifier<?> getOrderSpecifier(int order) {
        SortObjProvider sort = SortObjProvider.values()[order];
        return sort.getOrderSpecifier();
    }

}

