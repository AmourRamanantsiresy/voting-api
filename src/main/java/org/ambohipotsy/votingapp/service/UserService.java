package org.ambohipotsy.votingapp.service;

import lombok.AllArgsConstructor;
import org.ambohipotsy.votingapp.model.exceptions.NotFoundException;
import org.ambohipotsy.votingapp.repository.UserRepository;
import org.ambohipotsy.votingapp.repository.entity.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;

    public User getOne(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id = " + userId + " not found"));
    }

    public User saveOne(User user) {
        return userRepository.save(user);
    }
}
