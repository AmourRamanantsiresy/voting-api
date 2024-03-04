package org.ambohipotsy.votingapp.model.rest.users;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.ambohipotsy.votingapp.model.rest.users.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AuthenticationResponse {
    private String token;
    private User user;
}
