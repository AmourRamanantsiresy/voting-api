package org.ambohipotsy.votingapp.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class VoteResult implements Serializable {
    private String name;
    private String id;
    private Integer votersCount;
    private LocalDateTime createdAt;
    private List<VoteSectionResult> sectionResults;
}
