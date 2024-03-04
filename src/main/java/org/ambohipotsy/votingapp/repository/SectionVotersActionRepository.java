package org.ambohipotsy.votingapp.repository;

import java.util.List;
import org.ambohipotsy.votingapp.repository.entity.SectionVotersAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionVotersActionRepository extends JpaRepository<SectionVotersAction, String> {
  List<SectionVotersAction> findAllByVoteSectionId(String voteId);
}
