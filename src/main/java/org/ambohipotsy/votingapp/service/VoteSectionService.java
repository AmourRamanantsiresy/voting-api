package org.ambohipotsy.votingapp.service;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteSectionService {
    private final VoteSectionRepository voteSectionRepository;
    private final VoteRepository voteRepository;

    public List<VoteSection> getAll(String voteId, String name) {
        if (name.isEmpty() || name.isBlank()) {
            return voteSectionRepository.findAllByVoteId(voteId);
        }
        return voteSectionRepository.findAllByVoteIdAndNameContains(voteId, name);
    }

    public VoteSection save(String voteId, VoteSection voteSection) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found."));
        voteSection.setVote(vote);
        return voteSectionRepository.save(voteSection);
    }
}
