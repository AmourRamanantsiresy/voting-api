package org.ambohipotsy.votingapp.controller.validator;

import static org.ambohipotsy.votingapp.utils.ValidatorUtilities.isStringValid;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.VoteSection;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteSectionValidator {
  public void validate(VoteSection voteSection) {
    StringBuilder error = new StringBuilder();

    if (!isStringValid(voteSection.getName())) {
      error.append("Vote section name is mandatory");
    }
    if (!error.isEmpty()) {
      throw new BadRequestException(error.toString());
    }
  }
}
