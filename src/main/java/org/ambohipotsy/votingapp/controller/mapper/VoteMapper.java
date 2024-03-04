package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteMapper {
  public Vote toRest(org.ambohipotsy.votingapp.repository.entity.Vote vote) {
    return Vote.builder()
        .votersCountAllowed(vote.getVotersCountAllowed())
        .id(vote.getId())
        .name(vote.getName())
        .createdAt(vote.getCreatedAt())
        .build();
  }

  public org.ambohipotsy.votingapp.repository.entity.Vote toDomain(Vote vote) {
    return org.ambohipotsy.votingapp.repository.entity.Vote.builder()
        .votersCountAllowed(vote.getVotersCountAllowed())
        .name(vote.getName())
        .id(vote.getId())
        .build();
  }
}
