package org.ambohipotsy.votingapp.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteCandidateResult;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class VoteWinnerService {
  public List<VoteCandidateResult> getWinners(
      List<VoteCandidateResult> voteCandidateResults,
      Integer votersCountAllowed,
      Integer totalVotersCount) {
    List<VoteCandidateResult> currentSortedVoteCandidateResult =
        voteCandidateResults.stream()
            .sorted(Comparator.comparingInt(VoteCandidateResult::getVotes).reversed())
            .toList();
    List<VoteCandidateResult> voteCandidateWinners = new ArrayList<>();

    if (voteCandidateResults.isEmpty()) return voteCandidateWinners;

    voteCandidateWinners.add(currentSortedVoteCandidateResult.get(0));
    Integer currentVotersCountAllowed = votersCountAllowed;

    for (int i = 1; i < currentSortedVoteCandidateResult.size(); i++) {
      VoteCandidateResult currentVoteCandidateResult = currentSortedVoteCandidateResult.get(i);
      VoteCandidateResult lastVoteCandidateWinner =
          voteCandidateWinners.get(voteCandidateWinners.size() - 1);

      if (i <= votersCountAllowed && lastVoteCandidateWinner.getVotes() * 2 < totalVotersCount) {
        currentVotersCountAllowed++;
      }

      if (voteCandidateWinners.size() < currentVotersCountAllowed) {
        voteCandidateWinners.add(currentVoteCandidateResult);
      } else if (Objects.equals(
          currentVoteCandidateResult.getVotes(), lastVoteCandidateWinner.getVotes())) {
        voteCandidateWinners.add(currentVoteCandidateResult);
      } else {
        break;
      }
    }
    return voteCandidateWinners;
  }
}
