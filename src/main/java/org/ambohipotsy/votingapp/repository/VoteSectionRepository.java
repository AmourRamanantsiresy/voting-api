package org.ambohipotsy.votingapp.repository;

import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteSectionRepository extends JpaRepository<VoteSection, String> {
    List<VoteSection> findAllByVoteId(String voteId);
    List<VoteSection> findAllByVoteIdAndNameContains(String voteId, String name);

}
