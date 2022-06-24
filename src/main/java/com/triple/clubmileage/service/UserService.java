package com.triple.clubmileage.service;

import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    public User read(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
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
