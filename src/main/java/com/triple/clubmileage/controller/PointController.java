package com.triple.clubmileage.controller;

import com.triple.clubmileage.dto.PointDTO;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final UserRepository userRepository;

    @GetMapping("/point/{userId}")
    public ResponseEntity getPoint(@PathVariable String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        return ResponseEntity.status(HttpStatus.OK).body(new PointDTO(user.getPoint()));
    }
}
