package org.ambohipotsy.votingapp.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

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
