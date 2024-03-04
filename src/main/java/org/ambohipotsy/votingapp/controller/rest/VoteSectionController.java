package org.ambohipotsy.votingapp.controller.rest;

import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.controller.mapper.VoteSectionMapper;
import org.ambohipotsy.votingapp.controller.validator.VoteSectionValidator;
import org.ambohipotsy.votingapp.model.rest.VoteSection;
import org.ambohipotsy.votingapp.service.VoteSectionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/vote/{voteId}/voteSection")
public class VoteSectionController {
  private final VoteSectionService voteSectionService;
  private final VoteSectionMapper voteSectionMapper;
  private final VoteSectionValidator voteSectionValidator;

  @PutMapping("")
  public VoteSection saveOne(@PathVariable String voteId, @RequestBody VoteSection voteSection) {
    voteSectionValidator.validate(voteSection);
    org.ambohipotsy.votingapp.repository.entity.VoteSection toSave =
        voteSectionMapper.toDomain(voteSection);
    return voteSectionMapper.toRest(voteSectionService.save(voteId, toSave));
  }

  @GetMapping("")
  public List<VoteSection> getAll(
      @PathVariable String voteId,
      @RequestParam(name = "name", required = false, defaultValue = "") String name) {
    return voteSectionService.getAll(voteId, name).stream().map(voteSectionMapper::toRest).toList();
  }
}
