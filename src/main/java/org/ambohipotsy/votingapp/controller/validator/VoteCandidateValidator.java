package org.ambohipotsy.votingapp.controller.validator;


import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.springframework.stereotype.Component;

import static org.ambohipotsy.votingapp.utils.ValidatorUtilities.isStringValid;

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
