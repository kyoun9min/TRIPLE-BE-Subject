package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Photo;
import com.triple.clubmileage.entity.PointHistory;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.repository.PhotoRepository;
import com.triple.clubmileage.repository.PointHistoryRepository;
import com.triple.clubmileage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final PhotoService photoService;

    private final PointHistoryService pointHistoryService;

    private final UserService userService;

    private final PlaceService placeService;

    public void create(EventDTO eventDTO) {
        int point = 0;

        point = pointHistoryService.calcPoint(eventDTO);

        Review review = Review.builder()
                .id(eventDTO.getReviewId())
                .content(eventDTO.getContent())
                .point(point)
                .photoList(photoService.getPhotoList(eventDTO.getAttachedPhotoIds()))
                .user(userService.read(eventDTO.getUserId()))
                .place(placeService.read(eventDTO.getPlaceId()))
                .build();
        reviewRepository.save(review);

        if (point > 0) {
            userService.savePoint(eventDTO.getUserId(), point);
        }
    }

    public void update(EventDTO eventDTO) {

    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(EventDTO eventDTO) {

    }
}
