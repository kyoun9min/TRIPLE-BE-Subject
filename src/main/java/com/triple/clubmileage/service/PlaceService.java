package com.triple.clubmileage.service;

import com.triple.clubmileage.entity.Place;
import com.triple.clubmileage.exception.PlaceNotFoundException;
import com.triple.clubmileage.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public Place read(String id) {
        return placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException("해당 장소가 존재하지 않습니다."));
    }

    public boolean isFirstReview(String id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException("해당 장소가 존재하지 않습니다."));
        if (place.getReviewList().isEmpty()) {
            return true;
        }
        return false;
    }
}
