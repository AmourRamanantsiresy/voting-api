package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.VoteSection;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
@Transactional
public class VoteSectionMapper {
    private final VoteSectionRepository voteRepository;

    public VoteSection toRest(org.ambohipotsy.votingapp.repository.entity.VoteSection voteSection) {
        return VoteSection.builder()
                .id(voteSection.getId())
                .name(voteSection.getName())
                .voteCountAllowed(voteSection.getVoteCountAllowed())
                .createdAt(voteSection.getCreatedAt())
                .build();
    }

    public org.ambohipotsy.votingapp.repository.entity.VoteSection toDomain(VoteSection vote) {
        org.ambohipotsy.votingapp.repository.entity.VoteSection mapped = new org.ambohipotsy.votingapp.repository.entity.VoteSection();
        if (vote.getId() != null) {
            Optional<org.ambohipotsy.votingapp.repository.entity.VoteSection> currentVoteSection = voteRepository.findById(vote.getId());
            mapped = currentVoteSection.orElseThrow(() -> new NotFoundException("Vote with id=" + vote.getId() + " not found."));
        }

        if (mapped.getVoteCountAllowed() == null && vote.getVoteCountAllowed() == null) {
            mapped.setVoteCountAllowed(1);
        } else if (vote.getVoteCountAllowed() != null) {
            mapped.setVoteCountAllowed(vote.getVoteCountAllowed());
        }

        mapped.setName(vote.getName());
        return mapped;
    }
}
