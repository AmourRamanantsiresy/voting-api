package org.ambohipotsy.votingapp.repository;

import java.util.List;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteSectionRepository extends JpaRepository<VoteSection, String> {
  List<VoteSection> findAllByVoteId(String voteId);

  List<VoteSection> findAllByVoteIdOrderByNameAsc(String voteId);

  List<VoteSection> findAllByVoteIdAndNameContains(String voteId, String name);
}
