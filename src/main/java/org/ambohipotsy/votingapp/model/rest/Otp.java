package org.ambohipotsy.votingapp.model.rest;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Otp {
    private String value;
    private String voteId;
}
