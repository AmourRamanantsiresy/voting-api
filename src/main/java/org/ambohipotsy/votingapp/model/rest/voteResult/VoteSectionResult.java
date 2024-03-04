package org.ambohipotsy.votingapp.model.rest.voteResult;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
  private Integer whiteVoteCount;
  private boolean needSecondVote;
  private List<VoteCandidateResult> voteCandidateWinners;
  private List<VoteCandidateResult> voteCandidateResults;
}
