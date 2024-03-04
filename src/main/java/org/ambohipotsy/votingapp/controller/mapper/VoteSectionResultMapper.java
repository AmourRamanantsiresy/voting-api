package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteCandidateResult;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteSectionResult;
import org.ambohipotsy.votingapp.repository.SectionVotersActionRepository;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.entity.SectionVotersAction;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteSectionResultMapper {
  private VoteCandidateRepository voteCandidateRepository;
  private VoteCandidateResultMapper voteCandidateResultMapper;
  private SectionVotersActionRepository sectionVotersActionRepository;

  public VoteSectionResult toRest(VoteSection voteSection, Integer totalVotersCount) {
    List<VoteCandidate> voteCandidates =
        voteCandidateRepository.findAllByVoteSectionId(voteSection.getId());
    List<SectionVotersAction> votersActions =
        sectionVotersActionRepository.findAllByVoteSectionId(voteSection.getId());
    return VoteSectionResult.builder()
        .name(voteSection.getName())
        .id(voteSection.getId())
        .votersCount(votersActions.size())
        .whiteVoteCount(totalVotersCount - votersActions.size())
        .voteCountAllowed(voteSection.getVoteCountAllowed())
        .voteCandidateResults(
            voteCandidates.stream()
                .map(voteCandidateResultMapper::toRest)
                .sorted(Comparator.comparingInt(VoteCandidateResult::getVotes).reversed())
                .toList())
        .build();
  }
}
