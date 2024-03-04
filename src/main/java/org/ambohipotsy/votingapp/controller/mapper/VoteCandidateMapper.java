package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.ambohipotsy.votingapp.utils.ImageUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteCandidateMapper {
    private final VoteSectionRepository voteSectionRepository;

    public VoteCandidate toRest(org.ambohipotsy.votingapp.repository.entity.VoteCandidate vote) {
        return VoteCandidate.builder()
                .id(vote.getId())
                .firstName(vote.getFirstName())
                .lastName(vote.getLastName())
                .voteSectionId(vote.getVoteSection().getId())
                .name(vote.getName())
                .picture(vote.getPicture())
                .createdAt(vote.getCreatedAt())
                .build();
    }

    public org.ambohipotsy.votingapp.repository.entity.VoteCandidate toDomain(String voteSectionId, VoteCandidate candidate) {
        VoteSection voteSection = voteSectionRepository.findById(voteSectionId).orElseThrow(() -> new NotFoundException("Vote section with id=" + candidate.getId() + " not found."));

        return org.ambohipotsy.votingapp.repository.entity.VoteCandidate.builder()
                .voteSection(voteSection)
                .picture(candidate.getPicture())
                .firstName(candidate.getFirstName())
                .lastName(candidate.getLastName())
                .name(candidate.getName())
                .build();
    }
}
