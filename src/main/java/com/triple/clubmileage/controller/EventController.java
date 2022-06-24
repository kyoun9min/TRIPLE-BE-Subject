package com.triple.clubmileage.controller;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.enumclass.EventAction;
import com.triple.clubmileage.enumclass.EventType;
import com.triple.clubmileage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final ReviewService reviewService;

    @PostMapping("/events")
    public ResponseEntity eventHandler(@RequestBody EventDTO eventDTO) {
        if (eventDTO.getType().equals(EventType.REVIEW)) {
            reviewParsing(eventDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private void reviewParsing(EventDTO eventDTO) {
        if (eventDTO.getAction().equals(EventAction.ADD)) {
            reviewService.duplicateCheck(eventDTO);
            reviewService.create(eventDTO);
        }
        else if (eventDTO.getAction().equals(EventAction.MOD)) {
            reviewService.update(eventDTO);
        }
        else if (eventDTO.getAction().equals(EventAction.DELETE)) {
            reviewService.delete(eventDTO);
        }
    }
}
