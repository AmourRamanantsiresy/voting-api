package org.ambohipotsy.votingapp.controller.validator;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;
import org.ambohipotsy.votingapp.model.rest.Vote;
import org.ambohipotsy.votingapp.repository.OtpRepository;
import org.ambohipotsy.votingapp.repository.entity.Otp;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.ambohipotsy.votingapp.utils.ValidatorUtilities.isStringValid;

@Component
@AllArgsConstructor
public class OtpValidator {
    private final OtpRepository otpRepository;

    public void validate(String key) {
        StringBuilder error = new StringBuilder();
        Optional<Otp> otp = otpRepository.getOtpByValue(key);
        if (otp.isEmpty()) {
            error.append("The specified key don't exists.");
        } else if (otp.get().isAlreadyUsed()) {
            error.append("The specified key is not valid anymore.");
        }

        if (!error.isEmpty()) {
            throw new BadRequestException(error.toString());
        }
    }
}
