package com.triple.clubmileage.dto;

import com.triple.clubmileage.enumclass.EventAction;
import com.triple.clubmileage.enumclass.EventType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EventDTO {

    private EventType type;

    private EventAction action;

    private String reviewId;

    private String content;

    private List<String> attachedPhotoIds;

    private String userId;

    private String placeId;

    @Builder
    public EventDTO(String reviewId, String userId, String placeId) {
        this.type = EventType.REVIEW;
        this.reviewId = reviewId;
        this.userId = userId;
        this.placeId = placeId;
        this.attachedPhotoIds = new ArrayList<>();
    }
}
