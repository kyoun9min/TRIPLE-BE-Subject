package com.triple.clubmileage.dto;

import com.triple.clubmileage.enumclass.EventAction;
import com.triple.clubmileage.enumclass.EventType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class EventDTO {

    private EventType type;

    private EventAction action;

    private String reviewId;

    private String content;

    private List<String> attachedPhotoIds;

    private String userId;

    private String placeId;
}
