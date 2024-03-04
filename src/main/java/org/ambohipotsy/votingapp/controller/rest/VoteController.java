package org.ambohipotsy.votingapp.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteMapper;
import org.ambohipotsy.votingapp.controller.validator.VoteValidator;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.ambohipotsy.votingapp.model.rest.VoteAction;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.service.VoteActionService;
import org.ambohipotsy.votingapp.service.VoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/vote")
public class VoteController {
  private final VoteService voteService;
  private final VoteActionService voteActionService;
  private final VoteMapper voteMapper;
  private final VoteValidator voteValidator;

  @PutMapping("")
  public Vote saveOne(@RequestBody Vote vote) {
    voteValidator.validate(vote);
    return voteMapper.toRest(voteService.saveOne(voteMapper.toDomain(vote)));
  }

  @PutMapping("/{voteId}/make")
  public void makeVote(@PathVariable String voteId, @RequestBody List<VoteAction> voteActions) {
    voteActionService.makeVote(voteId, voteActions);
  }

  @GetMapping("")
  public List<Vote> getAll(
      @RequestParam(name = "name", required = false, defaultValue = "") String name) {
    return voteService.getAll(name).stream().map(voteMapper::toRest).toList();
  }

  @GetMapping("/{voteId}/result")
  public VoteResult getResult(@PathVariable String voteId) {
    return voteService.getResult(voteId);
  }

  @GetMapping("/{voteId}")
  public Vote getOne(@PathVariable String voteId) {
    return voteMapper.toRest(voteService.getOne(voteId));
  }
}
