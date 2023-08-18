package net.rooban.springuserrole.repository;

import net.rooban.springuserrole.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User getUserByUsername(String username);
}
