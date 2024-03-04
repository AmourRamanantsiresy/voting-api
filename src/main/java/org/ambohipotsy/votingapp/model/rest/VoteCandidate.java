package org.ambohipotsy.votingapp.model.rest;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class VoteCandidate implements Serializable {
  private String id;
  private String name;
  private String firstName;
  private String lastName;
  private String picture;
  private String voteSectionId;
  private LocalDateTime createdAt;
}
