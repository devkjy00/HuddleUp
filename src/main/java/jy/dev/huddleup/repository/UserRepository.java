package jy.dev.huddleup.repository;

import java.util.Optional;
import jy.dev.huddleup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialProviderKey(String socialProviderKey);

    Optional<User> findByUsername(String username);
}
