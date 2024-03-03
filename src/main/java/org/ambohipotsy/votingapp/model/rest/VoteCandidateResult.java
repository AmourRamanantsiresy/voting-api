package org.ambohipotsy.votingapp.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class VoteCandidateResult implements Serializable {
    private String name;
    private String id;
    private String picture;
    private String firstName;
    private String lastName;
    private Integer votes;
}
