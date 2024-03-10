package org.ambohipotsy.votingapp.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteResult;
import org.ambohipotsy.votingapp.model.rest.voteResult.VoteSectionResult;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NextVoteService {
  private final VoteRepository voteRepository;
  private final VoteSectionRepository voteSectionRepository;
  private final VoteCandidateRepository voteCandidateRepository;
  private final VoteService voteService;
  private final Pattern NAME_PATTERN = Pattern.compile("(.*vote-)(?<id>[0-9].*)");

  @Transactional
  public void createSecondVote(String voteId) {
    VoteResult voteResult = this.voteService.getResult(voteId);
    Vote voteNext = this.getSecondVote(voteResult);
    if (voteNext == null) {
      throw new BadRequestException("The vote with id=" + voteId + " does not need 2nd tour.");
    }
    List<VoteSection> voteSectionsNext =
        this.getSecondVoteSections(voteResult.getSectionResults(), voteNext);
    this.getSecondVoteCandidate(voteResult, voteSectionsNext);
  }

  private Vote getSecondVote(VoteResult voteResult) {
    for (int i = 0; i < voteResult.getSectionResults().size(); i++) {
      if (voteResult.getSectionResults().get(i).isNeedSecondVote()) {
        return this.voteRepository.save(
            Vote.builder()
                .votersCountAllowed(voteResult.getVotersCount())
                .name(this.getVoteNewName(voteResult.getName()))
                .isDone(false)
                .build());
      }
    }
    return null;
  }

  private List<VoteSection> getSecondVoteSections(
      List<VoteSectionResult> voteSectionResults, Vote vote) {
    List<VoteSection> voteSectionsNext = new ArrayList<>();
    voteSectionResults.forEach(
        voteSectionResult -> {
          if (voteSectionResult.isNeedSecondVote()) {
            voteSectionsNext.add(
                VoteSection.builder()
                    .name(voteSectionResult.getName())
                    .voteCountAllowed(voteSectionResult.getVoteCountAllowed())
                    .vote(vote)
                    .build());
          }
        });

    return this.voteSectionRepository.saveAll(voteSectionsNext);
  }

  private void getSecondVoteCandidate(VoteResult voteResult, List<VoteSection> voteSectionsNext) {
    List<VoteSectionResult> voteSectionResults = voteResult.getSectionResults();
    List<VoteCandidate> voteCandidatesNext = new ArrayList<>();
    voteSectionResults.forEach(
        voteSectionResult -> {
          if (voteSectionResult.isNeedSecondVote()) {
            VoteSection associatedVoteSection =
                this.getAssociatedVoteSection(voteSectionResult.getName(), voteSectionsNext);
            voteSectionResult
                .getVoteCandidateWinners()
                .forEach(
                    voteCandidateResult -> {
                      if (voteCandidateResult.getVotes() * 2 <= voteResult.getVotersCount()) {
                        voteCandidatesNext.add(
                            VoteCandidate.builder()
                                .voteSection(associatedVoteSection)
                                .picture(voteCandidateResult.getPicture())
                                .name(voteCandidateResult.getName())
                                .build());
                      }
                    });
          }
        });
    this.voteCandidateRepository.saveAll(voteCandidatesNext);
  }

  private VoteSection getAssociatedVoteSection(
      String voteResultName, List<VoteSection> voteSections) {
    for (VoteSection voteSection : voteSections) {
      if (voteSection.getName().equals(voteResultName)) {
        return voteSection;
      }
    }
    return null;
  }

  public String getVoteNewName(String name) {
    Matcher matcher = this.NAME_PATTERN.matcher(name);
    if (!matcher.find()) {
      return name + " vote-2";
    }
    return matcher.replaceAll(
        e -> {
          int next = Integer.parseInt(e.group(2)) + 1;
          return e.group(1) + next;
        });
  }
}
