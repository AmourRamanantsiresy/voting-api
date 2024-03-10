package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import java.text.DecimalFormat;
import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteCandidateResult;
import org.ambohipotsy.votingapp.repository.VoteActionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteAction;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteCandidateResultMapper {
  private final DecimalFormat decimalFormat = new DecimalFormat("#.##");
  private final VoteActionRepository voteActionRepository;

  public VoteCandidateResult toRest(VoteCandidate currentVoteCandidate, Integer totalVoters) {
    List<VoteAction> voteActions =
        voteActionRepository.findAllByVoteCandidateId(currentVoteCandidate.getId());

    return VoteCandidateResult.builder()
        .votes(voteActions.size())
        .lastName(currentVoteCandidate.getLastName())
        .firstName(currentVoteCandidate.getFirstName())
        .name(currentVoteCandidate.getName())
        .id(currentVoteCandidate.getId())
        .votesInPercent(
            Double.parseDouble(decimalFormat.format((100L * voteActions.size()) / totalVoters)))
        .picture(currentVoteCandidate.getPicture())
        .build();
  }
}
