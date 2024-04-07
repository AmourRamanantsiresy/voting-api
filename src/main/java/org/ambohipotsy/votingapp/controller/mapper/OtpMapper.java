package org.ambohipotsy.votingapp.controller.mapper;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.rest.Otp;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class OtpMapper {
    public Otp toRest(org.ambohipotsy.votingapp.repository.entity.Otp otp) {
        return Otp.builder()
                .value(otp.getValue())
                .voteId(otp.getVote().getId())
                .build();
    }
}
