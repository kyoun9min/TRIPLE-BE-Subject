package com.triple.clubmileage.controller;

import com.triple.clubmileage.dto.PointDTO;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.exception.UserNotFoundException;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class PointController {

    private final UserRepository userRepository;

    @GetMapping("/point/{userId}")
    public ResponseEntity getPoint(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
        return ResponseEntity.status(HttpStatus.OK).body(new PointDTO(user.getPoint()));
    }
}
