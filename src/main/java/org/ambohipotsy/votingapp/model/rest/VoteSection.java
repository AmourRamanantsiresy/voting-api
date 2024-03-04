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
public class VoteSection implements Serializable {
  private String id;
  private String name;
  private Integer voteCountAllowed;
  private LocalDateTime createdAt;
}
