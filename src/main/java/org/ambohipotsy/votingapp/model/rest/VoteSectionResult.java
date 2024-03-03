package org.ambohipotsy.votingapp.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class VoteSectionResult implements Serializable {
    private String name;
    private String id;
    private Integer voteCountAllowed;
    private Integer votersCount;
    private List<VoteCandidateResult> voteCandidateResults;
}
