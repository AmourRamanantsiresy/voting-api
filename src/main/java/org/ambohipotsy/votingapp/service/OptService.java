package org.ambohipotsy.votingapp.service;


import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.repository.OtpRepository;
import org.ambohipotsy.votingapp.repository.VoteRepository;
import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.ambohipotsy.votingapp.repository.entity.Vote;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class OptService {
    private final OtpRepository otpRepository;
    private final VoteRepository voteRepository;
    private final int OTP_LENGTH = 3;
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz0123456789";

    private Otp generateOne(Vote vote) {
        String key = generateKey();
        boolean alreadyExist = otpRepository.existsByValue(key);
        if (alreadyExist) {
            return generateOne(vote);
        }
        return this.otpRepository.save(Otp.builder()
                .isInvalid(false)
                .value(key)
                .vote(vote)
                .build());
    }

    public Otp generateOneWithVote(String voteId) {
        Vote currentvote = voteRepository.findById(voteId).orElseThrow(() -> new NotFoundException("Vote with id=" + voteId + " not found."));
        return generateOne(currentvote);
    }

    private String generateKey() {
        Random random = new Random();
        StringBuilder keyBuilder = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            keyBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return keyBuilder.toString();
    }

    public void invalidateOtp(String key) {
        Otp otp = otpRepository.getOtpByValue(key).orElseThrow(() -> new BadRequestException("The specified key don't exists."));
        otp.setInvalid(true);
        otpRepository.save(otp);
    }

    public Otp getOneByValue(String value) {
        return otpRepository.getOtpByValue(value).orElseThrow(() -> new NotFoundException("The specified key don't exist."));
    }
}
