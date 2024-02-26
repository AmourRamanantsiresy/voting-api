package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
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

    public org.ambohipotsy.votingapp.repository.entity.VoteCandidate toDomain(VoteCandidate candidate) {
        Optional<VoteSection> voteSection = voteSectionRepository.findById(candidate.getId());
        Optional<org.ambohipotsy.votingapp.repository.entity.VoteCandidate> voteCandidate = voteCandidateRepository.findById(candidate.getId());

        return org.ambohipotsy.votingapp.repository.entity.VoteCandidate.builder()
                .voteSection(voteSection.orElse(null))
                .voteCount(voteCandidate.map(org.ambohipotsy.votingapp.repository.entity.VoteCandidate::getVoteCount).orElse(0))
                .picture(candidate.getPicture())
                .name(candidate.getName())
                .build();
    }
}
