package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.VotersAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotersActionRepository extends JpaRepository<VotersAction, String> {
    List<VotersAction> findAllByVoteId(String voteId);
}
