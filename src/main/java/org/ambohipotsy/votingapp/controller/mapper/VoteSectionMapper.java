package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteSection;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.springframework.stereotype.Component;

import java.util.List;
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
                .createdAt(voteSection.getCreatedAt())
                .build();
    }

    public org.ambohipotsy.votingapp.repository.entity.VoteSection toDomain(VoteSection vote) {
        Optional<org.ambohipotsy.votingapp.repository.entity.VoteSection> currentVoteSection = voteRepository.findById(vote.getId());
        org.ambohipotsy.votingapp.repository.entity.VoteSection mapped = new org.ambohipotsy.votingapp.repository.entity.VoteSection();

        if (currentVoteSection.isPresent()) {
            mapped = currentVoteSection.get();
        }

        mapped.setName(vote.getName());

        return mapped;
    }
}
