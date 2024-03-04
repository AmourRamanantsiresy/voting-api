package org.ambohipotsy.votingapp.controller.validator;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.service.NextVoteService;
import org.ambohipotsy.votingapp.service.VoteService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NextVoteValidator {
  private VoteService voteService;
  private VoteRepository voteRepository;
  private NextVoteService nextVoteService;

  public void validate(String voteId) {
    Vote vote = this.voteService.getOne(voteId);
    Optional<Vote> alreadySavedNext =
        this.voteRepository.findVoteByNameEquals(
            this.nextVoteService.getVoteNewName(vote.getName()));

    if (alreadySavedNext.isPresent()) {
      throw new BadRequestException("Next vote for id=" + voteId + " already exist.");
    }
  }
}
