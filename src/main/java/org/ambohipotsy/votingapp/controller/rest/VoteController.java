package org.ambohipotsy.votingapp.controller.rest;


import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteMapper;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.ambohipotsy.votingapp.service.VoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vote")
public class VoteController {
    private final VoteService voteService;
    private final VoteMapper voteMapper;

    @PutMapping("/")
    public Vote saveOne(@RequestBody Vote vote) {
        return voteMapper.toRest(voteService.saveOne(voteMapper.toDomain(vote)));
    }

    @GetMapping("/")
    public List<Vote> getAll(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        return voteService.getAll(name).stream().map(voteMapper::toRest).toList();
    }
}
