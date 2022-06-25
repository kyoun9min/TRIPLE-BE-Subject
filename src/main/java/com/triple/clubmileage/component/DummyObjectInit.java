package com.triple.clubmileage.component;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Place;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.repository.PlaceRepository;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyObjectInit {

    private final UserRepository userRepository;

    private final PlaceRepository placeRepository;

    public void init(EventDTO eventDTO) {

        String userId = eventDTO.getUserId();
        if (!userRepository.existsById(userId)) {
            User user = new User(userId);
            userRepository.save(user);
        }

        String placeId = eventDTO.getPlaceId();
        if (!placeRepository.existsById(placeId)) {
            Place place = new Place(placeId);
            placeRepository.save(place);
        }
    }
}
