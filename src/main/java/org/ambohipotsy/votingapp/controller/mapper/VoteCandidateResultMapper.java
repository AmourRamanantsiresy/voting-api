package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteCandidateResult;
import org.ambohipotsy.votingapp.repository.VoteActionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteAction;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class VoteCandidateResultMapper {
    private final VoteActionRepository voteActionRepository;

    public VoteCandidateResult toRest(VoteCandidate currentVoteCandidate) {
        List<VoteAction> voteActions = voteActionRepository.findAllByVoteCandidateId(currentVoteCandidate.getId());

        return VoteCandidateResult.builder()
                .votes(voteActions.size())
                .name(currentVoteCandidate.getName())
                .id(currentVoteCandidate.getId())
                .build();
    }
}
