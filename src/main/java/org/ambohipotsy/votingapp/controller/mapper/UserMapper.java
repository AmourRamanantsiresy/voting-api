package org.ambohipotsy.votingapp.controller.mapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.model.rest.users.SignUser;
import org.ambohipotsy.votingapp.model.rest.users.User;
import org.ambohipotsy.votingapp.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Transactional
public class UserMapper {
    private UserRepository userRepository;

    public User toRest(org.ambohipotsy.votingapp.repository.entity.User user) {
        return User.builder()
                .id(user.getId())
                .role(user.getDEFAULT_AUTHORITY())
                .username(user.getUsername())
                .build();
    }

    public org.ambohipotsy.votingapp.repository.entity.User toDomain(User user) {
        org.ambohipotsy.votingapp.repository.entity.User currentUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException("User with id = " + user.getId() + " not found."));

        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());

        return currentUser;
    }


    public org.ambohipotsy.votingapp.repository.entity.User toDomain(SignUser user) {
        return org.ambohipotsy.votingapp.repository.entity.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
