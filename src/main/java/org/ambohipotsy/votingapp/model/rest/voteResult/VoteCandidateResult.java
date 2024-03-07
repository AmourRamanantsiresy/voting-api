package org.ambohipotsy.votingapp.model.rest.voteResult;

import java.io.Serializable;
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
public class VoteCandidateResult implements Serializable {
  private String name;
  private String id;
  private String picture;
  private String firstName;
  private String lastName;
  private Integer votes;
  private double votesInPercent;
}
