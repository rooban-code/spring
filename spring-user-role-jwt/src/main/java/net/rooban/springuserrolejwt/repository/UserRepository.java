package net.rooban.springuserrolejwt.repository;

import net.rooban.springuserrolejwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username);

    Optional<User> findByUsername(String username);
}
