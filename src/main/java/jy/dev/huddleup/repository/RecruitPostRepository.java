package jy.dev.huddleup.repository;

import jy.dev.huddleup.model.RecruitPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostRepository extends JpaRepository<RecruitPost, Long> {
}
