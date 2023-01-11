package jy.dev.huddleup.repository;

import java.util.List;
import jy.dev.huddleup.model.RecruitPost;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPost, Long> {

    List<RecruitPost> findAllByIdIn(List<Long> ids, Sort sort);
}
