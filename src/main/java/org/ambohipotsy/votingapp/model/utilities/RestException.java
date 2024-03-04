package org.ambohipotsy.votingapp.model.utilities;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestException implements Serializable {
  private Integer code;
  private String status;
  private String message;
}
