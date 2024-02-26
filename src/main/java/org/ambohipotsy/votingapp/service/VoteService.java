package org.ambohipotsy.votingapp.service;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteResultMapper;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.VoteResult;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final VoteResultMapper voteResultMapper;

    public Vote saveOne(Vote vote) {
        return voteRepository.save(vote);
    }

    public List<Vote> getAll(String name) {
        if (name.isBlank() || name.isEmpty()) {
            return voteRepository.findAll();
        }
        return voteRepository.findAllByNameContainsIgnoreCase(name);
    }

    public Vote getOne(String id) {
        Optional<Vote> vote = voteRepository.findById(id);
        if (vote.isEmpty()) {
            throw new NotFoundException("Vote with id=" + id + " not found.");
        }
        return vote.get();
    }

    public VoteResult getResult(String voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found"));
        return voteResultMapper.toRest(vote);
    }
}
