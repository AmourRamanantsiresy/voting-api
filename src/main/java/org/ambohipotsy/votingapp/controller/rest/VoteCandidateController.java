package org.ambohipotsy.votingapp.controller.rest;


import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteCandidateMapper;
import org.ambohipotsy.votingapp.controller.validator.VoteCandidateValidator;
import org.ambohipotsy.votingapp.model.rest.VoteCandidate;
import org.ambohipotsy.votingapp.service.VoteCandidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/voteSection/{voteSectionId}")
public class VoteCandidateController {
    private final VoteCandidateService voteCandidateService;
    private final VoteCandidateMapper voteCandidateMapper;
    private final VoteCandidateValidator voteCandidateValidator;

    @PutMapping("")
    public List<VoteCandidate> saveAll(@PathVariable String voteSectionId, @RequestBody List<VoteCandidate> voteCandidates) {
        voteCandidates.forEach(voteCandidateValidator::validate);
        List<org.ambohipotsy.votingapp.repository.entity.VoteCandidate> toSave = voteCandidates.stream().map(e -> voteCandidateMapper.toDomain(voteSectionId, e)).toList();
        return voteCandidateService.saveAll(voteSectionId, toSave).stream().map(voteCandidateMapper::toRest).toList();
    }

    @GetMapping("")
    public List<VoteCandidate> getAll(
            @PathVariable String voteSectionId,
            @RequestParam(name = "name", required = false, defaultValue = "") String name
    ) {
        return voteCandidateService.getAll(voteSectionId, name).stream().map(voteCandidateMapper::toRest).toList();
    }
}
