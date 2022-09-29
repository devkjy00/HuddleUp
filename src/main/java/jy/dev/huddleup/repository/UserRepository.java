package jy.dev.huddleup.repository;

import jy.dev.huddleup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.nio.channels.FileChannel;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialProviderKey(String socialProviderKey);
    Optional<User> findByUsername(String username);
}
