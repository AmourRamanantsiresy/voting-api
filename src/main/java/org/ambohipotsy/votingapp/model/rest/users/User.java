package org.ambohipotsy.votingapp.model.rest.users;

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
public class User implements Serializable {
  private String id;
  private String username;
  private String role;
}
