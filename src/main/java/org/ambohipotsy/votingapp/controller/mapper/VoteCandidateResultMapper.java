package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteCandidateResult;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteCandidateResultMapper {

    public VoteCandidateResult toRest(VoteCandidate currentVoteCandidate) {
        return VoteCandidateResult.builder()
                .votes(currentVoteCandidate.getVoteCount())
                .name(currentVoteCandidate.getName())
                .id(currentVoteCandidate.getId())
                .build();
    }
}
