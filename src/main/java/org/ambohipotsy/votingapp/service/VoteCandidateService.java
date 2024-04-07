package org.ambohipotsy.votingapp.service;

import jakarta.transaction.Transactional;
import java.util.List;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteCandidateService {
  private final VoteCandidateRepository voteCandidateRepository;
  private final VoteSectionRepository voteSectionRepository;

  public List<VoteCandidate> getAll(String voteSectionId, String name) {
    if (name.isBlank() || name.isEmpty()) {
      return voteCandidateRepository.findAllByVoteSectionIdOrderByNameAsc(voteSectionId);
    }
    return voteCandidateRepository.findAllByVoteSectionIdAndNameContainsIgnoreCase(
        voteSectionId, name);
  }

  @Transactional
  public List<VoteCandidate> saveAll(String voteSectionId, List<VoteCandidate> voteCandidates) {
    VoteSection voteSection =
        voteSectionRepository
            .findById(voteSectionId)
            .orElseThrow(
                () ->
                    new NotFoundException("Vote section with id=" + voteSectionId + " not found."));
    voteCandidates.forEach(voteCandidate -> voteCandidate.setVoteSection(voteSection));
    return voteCandidateRepository.saveAll(voteCandidates);
  }
}
