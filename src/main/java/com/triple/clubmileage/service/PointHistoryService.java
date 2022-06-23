package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.PointHistory;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.enumclass.PointReason;
import com.triple.clubmileage.enumclass.PointStatus;
import com.triple.clubmileage.repository.PointHistoryRepository;
import com.triple.clubmileage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PointHistoryService {

    private final PointHistoryRepository pointHistoryRepository;

    private final UserRepository userRepository;

    private final PhotoService photoService;

    private final PlaceService placeService;

    public int calcPoint(EventDTO eventDTO) {
        int point = 0;

        if (eventDTO.getContent().length() > 0) {
            point += 1;
            writeText(eventDTO);
        }
        if (eventDTO.getAttachedPhotoIds().size() > 0) {
            point += 1;
            photoService.create(eventDTO);
            attachPhoto(eventDTO);
        }
        if (placeService.isFirstReview(eventDTO.getPlaceId())) {
            point += 1;
            firstReview(eventDTO);
        }
        return point;
    }

    public void writeText(EventDTO eventDTO) {
        PointHistory pointHistory = PointHistory.builder()
                .user(userRepository.findById(eventDTO.getUserId()).get())
                .status(PointStatus.ADD.getTitle())
                .detail(PointStatus.ADD.getDetail())
                .reason(PointReason.WRITE_TEXT.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void attachPhoto(EventDTO eventDTO) {
        PointHistory pointHistory = PointHistory.builder()
                .user(userRepository.findById(eventDTO.getUserId()).get())
                .status(PointStatus.ADD.getTitle())
                .detail(PointStatus.ADD.getDetail())
                .reason(PointReason.ATTACH_PHOTO.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void firstReview(EventDTO eventDTO) {
        PointHistory pointHistory = PointHistory.builder()
                .user(userRepository.findById(eventDTO.getUserId()).get())
                .status(PointStatus.ADD.getTitle())
                .detail(PointStatus.ADD.getDetail())
                .reason(PointReason.FIRST_REVIEW.getDescription())
                .build();
        pointHistoryRepository.save(pointHistory);
    }

    public void deletePhoto(EventDTO eventDTO) {

    }
}
