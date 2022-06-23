package com.triple.clubmileage.service;

import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public User read(UUID id) {
        return userRepository.findById(id).get();
    }

    public void savePoint(UUID id, int point) {
        User user = userRepository.findById(id).get();
        user.addPoint(point);
        userRepository.save(user);
    }
}
