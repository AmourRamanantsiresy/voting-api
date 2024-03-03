package org.ambohipotsy.votingapp.controller.mapper;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteResult;
import org.ambohipotsy.votingapp.model.rest.VoteSectionResult;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.VotersActionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.ambohipotsy.votingapp.repository.entity.VotersAction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Transactional
public class VoteResultMapper {
    private final VoteSectionRepository voteSectionRepository;
    private final VoteSectionResultMapper voteSectionResultMapper;
    private final VotersActionRepository votersActionRepository;

    public VoteResult toRest(Vote vote) {
        List<VoteSection> voteSections = voteSectionRepository.findAllByVoteId(vote.getId());
        List<VoteSectionResult> voteSectionResults = voteSections.stream().map(voteSectionResultMapper::toRest).toList();
        List<VotersAction> votersActions = votersActionRepository.findAllByVoteId(vote.getId());

        return VoteResult.builder()
                .id(vote.getId())
                .name(vote.getName())
                .createdAt(vote.getCreatedAt())
                .votersCount(votersActions.size())
                .sectionResults(voteSectionResults)
                .build();
    }
}
