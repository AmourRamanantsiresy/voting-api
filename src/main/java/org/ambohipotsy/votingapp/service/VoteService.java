package org.ambohipotsy.votingapp.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteResultMapper;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteResultMapper voteResultMapper;
    private final VoteSectionRepository voteSectionRepository;
    private final VoteCandidateRepository voteCandidateRepository;

    public Vote saveOne(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> getAll(String name) {
        if (name.isBlank() || name.isEmpty()) {
            return voteRepository.findAll();
        }
        return voteRepository.findAllByNameContainsIgnoreCase(name);
    }

    @Transactional
    public void doneVote(String voteId) {
        Vote vote = this.voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found."));
        vote.setDone(true);
        this.voteRepository.save(vote);
    }

    public Vote getOne(String voteId){
        return voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found"));
    }

    public VoteResult getResult(String voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found"));
        return voteResultMapper.toRest(vote);
    }
}
