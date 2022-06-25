package com.triple.clubmileage.service;

import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.exception.UserNotFoundException;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public User read(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
    }

    public void savePoint(String id, int point) {
        User user = read(id);
        user.addPoint(point);
        userRepository.save(user);
    }

    public void deductionPoint(String id, int point) {
        User user = read(id);
        user.subPoint(point);
        userRepository.save(user);
    }
}
