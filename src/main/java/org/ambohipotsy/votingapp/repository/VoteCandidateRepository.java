package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteCandidateRepository extends JpaRepository<VoteCandidate, String> {
    List<VoteCandidate> findAllByVoteSectionId(String voteSectionId);
    List<VoteCandidate> findAllByVoteSectionIdAndNameContainsIgnoreCase(String voteSectionId, String name);

}
