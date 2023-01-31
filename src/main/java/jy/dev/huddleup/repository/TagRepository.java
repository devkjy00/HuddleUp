package jy.dev.huddleup.repository;

import java.util.Optional;
import jy.dev.huddleup.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query("select distinct t from Tag t "
        + "left join t.recruitPostTag "
        + "where t.id = ?1")
    Optional<Tag> find(Long id);

}
