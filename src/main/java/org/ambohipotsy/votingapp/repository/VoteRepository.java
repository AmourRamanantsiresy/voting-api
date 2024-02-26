package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<Vote, String> {
    List<Vote> findAllByNameContainsIgnoreCase(String name);
}
