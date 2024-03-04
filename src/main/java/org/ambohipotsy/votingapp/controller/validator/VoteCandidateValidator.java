package org.ambohipotsy.votingapp.controller.validator;

import static org.ambohipotsy.votingapp.utils.ValidatorUtilities.isStringValid;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteCandidateValidator {
  public void validate(VoteCandidate vote) {
    StringBuilder error = new StringBuilder();

    if (!isStringValid(vote.getName())) {
      error.append("Vote candidate name is mandatory");
    }

    if (!error.isEmpty()) {
      throw new BadRequestException(error.toString());
    }
  }
}
