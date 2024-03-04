package org.ambohipotsy.votingapp.model.rest;

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
public class VoteAction implements Serializable {
  private String sectionId;
  private List<String> candidateIds;
}
