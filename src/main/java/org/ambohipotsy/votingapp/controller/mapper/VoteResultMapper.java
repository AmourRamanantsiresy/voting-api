package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteSectionResult;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.VotersActionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.ambohipotsy.votingapp.repository.entity.VotersAction;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class VoteResultMapper {
  private final VoteSectionRepository voteSectionRepository;
  private final VoteSectionResultMapper voteSectionResultMapper;
  private final VotersActionRepository votersActionRepository;

  public VoteResult toRest(Vote vote) {
    List<VoteSection> voteSections =
        voteSectionRepository.findAllByVoteIdOrderByNameAsc(vote.getId());
    List<VotersAction> votersActions = votersActionRepository.findAllByVoteId(vote.getId());
    List<VoteSectionResult> voteSectionResults =
        voteSections.stream()
            .map(
                voteSection ->
                    this.voteSectionResultMapper.toRest(voteSection, votersActions.size()))
            .toList();

    return VoteResult.builder()
        .id(vote.getId())
        .name(vote.getName())
        .createdAt(vote.getCreatedAt())
        .votersCount(votersActions.size())
        .sectionResults(voteSectionResults)
        .build();
  }
}
