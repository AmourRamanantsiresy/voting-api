package org.ambohipotsy.votingapp.repository;

import java.util.Optional;
import org.ambohipotsy.votingapp.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findByUsername(String username);
}
