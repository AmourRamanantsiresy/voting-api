package org.ambohipotsy.votingapp.repository;

import java.util.List;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteCandidateRepository extends JpaRepository<VoteCandidate, String> {
  List<VoteCandidate> findAllByVoteSectionId(String voteSectionId);

  List<VoteCandidate> findAllByVoteSectionIdOrderByNameAsc(String voteSectionId);

  List<VoteCandidate> findAllByVoteSectionIdAndNameContainsIgnoreCase(
      String voteSectionId, String name);
}
