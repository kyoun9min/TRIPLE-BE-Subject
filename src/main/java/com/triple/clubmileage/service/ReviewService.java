package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Photo;
import com.triple.clubmileage.entity.PointHistory;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.repository.PhotoRepository;
import com.triple.clubmileage.repository.PointHistoryRepository;
import com.triple.clubmileage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

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
                .photoNumber(eventDTO.getAttachedPhotoIds().size())
                .point(point)
                .photoList(null)
                .user(userService.read(eventDTO.getUserId()))
                .place(placeService.read(eventDTO.getPlaceId()))
                .build();

        reviewRepository.save(review);
        photoService.create(eventDTO);

        if (point > 0) {
            userService.savePoint(eventDTO.getUserId(), point);
        }
    }

    public Review read(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }

    public void update(EventDTO eventDTO) {

        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(()->new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        List<Photo> photoList = photoService.getPhotoList(eventDTO.getAttachedPhotoIds());
        review.updateReview(eventDTO, photoList);

        // 글만 있는데 사진 추가 +1
        if (review.getContent().length() > 0 && review.getPhotoNumber() == 0 && eventDTO.getAttachedPhotoIds().size() > 0) {
            // 유저 +1, 리뷰 +1, 포인트 히스토리
            userService.savePoint(eventDTO.getUserId(), 1);
            review.addPoint(1);
            User user = userService.read(eventDTO.getUserId());
            pointHistoryService.attachPhoto(user);
        }

        // 사진 모두 삭제
        if (review.getPhotoNumber() > 0 && eventDTO.getAttachedPhotoIds().size() == 0) {
            // 유저 -1, 리뷰 -1, 포인트 히스토리
            userService.deductionPoint(eventDTO.getUserId(), 1);
            review.subPoint(1);
            User user = userService.read(eventDTO.getUserId());
            pointHistoryService.deletePhoto(user);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(EventDTO eventDTO) {
        // 유저 포인트 차감, 포인트 히스토리, 리뷰 삭제
        Review review = read(eventDTO.getReviewId());
        User user = userService.read(eventDTO.getUserId());
        int point = review.getPoint();

        userService.deductionPoint(eventDTO.getUserId(), point);
        pointHistoryService.deleteReview(user, point);
        reviewRepository.delete(review);
    }
}
