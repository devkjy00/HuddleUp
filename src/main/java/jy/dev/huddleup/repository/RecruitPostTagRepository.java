package jy.dev.huddleup.repository;

import jy.dev.huddleup.model.RecruitPostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitPostTagRepository extends JpaRepository<RecruitPostTag, Long> {

}
