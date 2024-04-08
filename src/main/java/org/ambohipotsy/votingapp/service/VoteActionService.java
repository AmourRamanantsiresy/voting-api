package org.ambohipotsy.votingapp.service;

import jakarta.transaction.Transactional;

import java.util.List;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.VoteAction;
import org.ambohipotsy.votingapp.repository.OtpRepository;
import org.ambohipotsy.votingapp.repository.SectionVotersActionRepository;
import org.ambohipotsy.votingapp.repository.VoteActionRepository;
import org.ambohipotsy.votingapp.repository.VoteCandidateRepository;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.VoteSectionRepository;
import org.ambohipotsy.votingapp.repository.VotersActionRepository;
import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.ambohipotsy.votingapp.repository.entity.SectionVotersAction;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.ambohipotsy.votingapp.repository.entity.VoteCandidate;
import org.ambohipotsy.votingapp.repository.entity.VoteSection;
import org.ambohipotsy.votingapp.repository.entity.VotersAction;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VoteActionService {
    private final VoteActionRepository voteActionRepository;
    private final VoteSectionRepository voteSectionRepository;
    private final VoteCandidateRepository voteCandidateRepository;
    private final VotersActionRepository votersActionRepository;
    private final SectionVotersActionRepository sectionVotersActionRepository;
    private final VoteRepository voteRepository;
    private final OtpRepository otpRepository;

    @Transactional
    public void makeVote(String voteId, List<VoteAction> voteActions, String otpValue) {
        Otp otp = otpValue == null ? null : otpRepository.getOtpByValue(otpValue).orElse(null);
        voteActions.forEach(vote -> voteOne(vote, otp));
        Vote vote =
                voteRepository
                        .findById(voteId)
                        .orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found."));
        votersActionRepository.save(VotersAction.builder().otp(otp).vote(vote).build());
    }

    public void removeVoteByOtp(String key) {
        Otp concernedOtp = otpRepository.getOtpByValue(key).orElseThrow(() -> new NotFoundException("The specified key don't exist."));
        voteActionRepository.deleteAllByOtpValue(key);
        votersActionRepository.deleteAllByOtpValue(key);
        concernedOtp.setInvalid(false);
        otpRepository.save(concernedOtp);
    }

    private void voteOne(VoteAction voteAction, Otp otp) {
        VoteSection voteSection =
                voteSectionRepository
                        .findById(voteAction.getSectionId())
                        .orElseThrow(
                                () ->
                                        new NotFoundException(
                                                "Vote section with id=" + voteAction.getSectionId() + " not found."));
        if (voteSection.getVoteCountAllowed() < voteAction.getCandidateIds().size()) {
            throw new BadRequestException(
                    "Should only vote for " + voteSection.getVoteCountAllowed() + " candidate at least.");
        }
        this.sectionVotersActionRepository.save(
                SectionVotersAction.builder().voteSection(voteSection).build());
        voteAction.getCandidateIds().forEach(candidate -> voteOneCandidate(candidate, otp));
    }

    private void voteOneCandidate(String candidateId, Otp otp) {
        VoteCandidate voteCandidate =
                voteCandidateRepository
                        .findById(candidateId)
                        .orElseThrow(
                                () ->
                                        new NotFoundException("Vote candidate with id=" + candidateId + " not found."));
        voteActionRepository.save(
                org.ambohipotsy.votingapp.repository.entity.VoteAction.builder()
                        .voteCandidate(voteCandidate)
                        .otp(otp)
                        .build());
    }
}
