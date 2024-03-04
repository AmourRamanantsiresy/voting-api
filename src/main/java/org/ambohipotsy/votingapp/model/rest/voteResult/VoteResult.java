package org.ambohipotsy.votingapp.model.rest.voteResult;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class VoteResult implements Serializable {
  private String name;
  private String id;
  private Integer votersCount;
  private LocalDateTime createdAt;
  private List<VoteSectionResult> sectionResults;
}
