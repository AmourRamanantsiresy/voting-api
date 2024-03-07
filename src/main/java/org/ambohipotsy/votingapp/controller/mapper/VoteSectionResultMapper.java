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
import org.ambohipotsy.votingapp.service.VoteWinnerService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteSectionResultMapper {
  private VoteCandidateRepository voteCandidateRepository;
  private VoteCandidateResultMapper voteCandidateResultMapper;
  private SectionVotersActionRepository sectionVotersActionRepository;
  private final VoteWinnerService voteWinnerService;

  public VoteSectionResult toRest(VoteSection voteSection, Integer totalVotersCount) {
    List<VoteCandidate> voteCandidates =
        voteCandidateRepository.findAllByVoteSectionId(voteSection.getId());
    List<SectionVotersAction> votersActions =
        sectionVotersActionRepository.findAllByVoteSectionId(voteSection.getId());
    List<VoteCandidateResult> voteCandidateResults =
        voteCandidates.stream()
            .map(voteCandidate -> voteCandidateResultMapper.toRest(voteCandidate, totalVotersCount))
            .sorted(Comparator.comparingInt(VoteCandidateResult::getVotes).reversed())
            .toList();
    List<VoteCandidateResult> voteCandidateWinners =
        voteWinnerService.getWinners(
            voteCandidateResults, voteSection.getVoteCountAllowed(), totalVotersCount);

    return VoteSectionResult.builder()
        .name(voteSection.getName())
        .id(voteSection.getId())
        .votersCount(votersActions.size())
        .voteCandidateWinners(voteCandidateWinners)
        .needSecondVote(voteSection.getVoteCountAllowed() < voteCandidateWinners.size())
        .whiteVoteCount(totalVotersCount - votersActions.size())
        .voteCountAllowed(voteSection.getVoteCountAllowed())
        .voteCandidateResults(voteCandidateResults)
        .build();
  }
}
