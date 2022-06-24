package com.triple.clubmileage.service;

import com.triple.clubmileage.entity.Place;
import com.triple.clubmileage.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place read(String id) {
        return placeRepository.findById(id).get();
    }

    public boolean isFirstReview(String id) {
        Place place = placeRepository.findById(id).get();
        if (place.getReviewList().isEmpty()) {
            return true;
        }
        return false;
    }
}
