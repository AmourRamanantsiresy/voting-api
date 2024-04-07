package org.ambohipotsy.votingapp.service;


import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.repository.OtpRepository;
import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@AllArgsConstructor
public class OptService {
    private final OtpRepository otpRepository;
    private final int OTP_LENGTH = 5;
    private final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public Otp generateOne(){
        String key = generateKey();
        boolean alreadyExist = otpRepository.existsByValue(key);
        if(alreadyExist){
            return generateOne();
        }
        return this.otpRepository.save(Otp.builder()
                        .isAlreadyUsed(false)
                        .value(key)
                .build());
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
}
