package org.ambohipotsy.votingapp.repository;

import java.util.List;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {
  List<Vote> findAllByNameContainsIgnoreCase(String name);
}
