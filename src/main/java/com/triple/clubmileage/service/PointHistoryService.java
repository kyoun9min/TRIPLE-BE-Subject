package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.PointHistory;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.enumclass.PointReason;
import com.triple.clubmileage.enumclass.PointStatus;
import com.triple.clubmileage.repository.PointHistoryRepository;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    private final UserService userService;

    private final PlaceService placeService;

    public int calcPoint(EventDTO eventDTO) {

        int point = 0;

        User user = userService.read(eventDTO.getUserId());

        if (eventDTO.getContent().length() > 0) {
            point += 1;
            writeText(user);
        }
        if (eventDTO.getAttachedPhotoIds().size() > 0) {
            point += 1;
            attachPhoto(user);
        }
        if (placeService.isFirstReview(eventDTO.getPlaceId())) {
            point += 1;
            firstReview(user);
        }
        return point;
    }

    public void writeText(User user) {

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .status(PointStatus.ADD.getTitle())
                .value(PointStatus.ADD.getValue())
                .reason(PointReason.WRITE_TEXT.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void attachPhoto(User user) {

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .status(PointStatus.ADD.getTitle())
                .value(PointStatus.ADD.getValue())
                .reason(PointReason.ATTACH_PHOTO.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void firstReview(User user) {

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .status(PointStatus.ADD.getTitle())
                .value(PointStatus.ADD.getValue())
                .reason(PointReason.FIRST_REVIEW.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void deletePhoto(User user) {

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .status(PointStatus.SUB.getTitle())
                .value(PointStatus.SUB.getValue())
                .reason(PointReason.DELETE_PHOTO.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void deleteReview(User user, int point) {

        PointHistory pointHistory = PointHistory.builder()
                .user(user)
                .status(PointStatus.SUB.getTitle())
                .value("-" + point)
                .reason(PointReason.DELETE_REVIEW.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }
}
