package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.PointHistory;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.enumclass.PointReason;
import com.triple.clubmileage.enumclass.PointStatus;
import com.triple.clubmileage.repository.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    private final UserService userService;

    private final PlaceService placeService;

    public int calcPoint(EventDTO eventDTO) {

        int point = 0;

        User user = userService.read(eventDTO.getUserId());

        if (eventDTO.getContent().length() > 0) { // 내용 1자 이상 +1
            point += 1;
            writeText(user);
        }
        if (eventDTO.getAttachedPhotoIds().size() > 0) { // 사진 1장 이상 +1
            point += 1;
            attachPhoto(user);
        }
        if (placeService.isFirstReview(eventDTO.getPlaceId())) { // 해당 장소의 첫 리뷰 +1
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
