package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteSectionResult;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class VoteSectionResultMapper {
    private VoteCandidateRepository voteCandidateRepository;
    private VoteCandidateResultMapper voteCandidateResultMapper;

    public VoteSectionResult toRest(VoteSection voteSection) {
        List<VoteCandidate> voteCandidates = voteCandidateRepository.findAllByVoteSectionId(voteSection.getId());

        return VoteSectionResult.builder()
                .name(voteSection.getName())
                .id(voteSection.getId())
                .voteCandidateResults(voteCandidates.stream().map(voteCandidateResultMapper::toRest).toList())
                .votersCount(voteCandidates.stream().map(VoteCandidate::getVoteCount).reduce(0, Integer::sum))
                .build();
    }
}
