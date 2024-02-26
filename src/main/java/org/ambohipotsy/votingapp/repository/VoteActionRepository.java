package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.VoteAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteActionRepository extends JpaRepository<VoteAction, String> {
    List<VoteAction> findAllByVoteCandidateId(String voteCandidateId);
}
