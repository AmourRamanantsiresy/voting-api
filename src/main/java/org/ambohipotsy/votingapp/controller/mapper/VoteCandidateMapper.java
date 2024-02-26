package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
public class VoteCandidateMapper {
    private final VoteCandidateRepository voteCandidateRepository;
    private final VoteSectionRepository voteSectionRepository;

    public VoteCandidate toRest(org.ambohipotsy.votingapp.repository.entity.VoteCandidate vote) {
        return VoteCandidate.builder()
                .id(vote.getId())
                .voteSectionId(vote.getVoteSection().getId())
                .name(vote.getName())
                .createdAt(vote.getCreatedAt())
                .build();
    }

    public org.ambohipotsy.votingapp.repository.entity.VoteCandidate toDomain(String voteSectionId, VoteCandidate candidate) {
        VoteSection voteSection = voteSectionRepository.findById(voteSectionId).orElseThrow(() -> new NotFoundException("Vote section with id=" + candidate.getId() + " not found."));

        return org.ambohipotsy.votingapp.repository.entity.VoteCandidate.builder()
                .voteSection(voteSection)
                .picture(candidate.getPicture())
                .name(candidate.getName())
                .build();
    }
}
