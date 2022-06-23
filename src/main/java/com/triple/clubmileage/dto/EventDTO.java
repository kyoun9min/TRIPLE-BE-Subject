package com.triple.clubmileage.dto;

import com.triple.clubmileage.enumclass.EventAction;
import com.triple.clubmileage.enumclass.EventType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class EventDTO {

    private EventType type;

    private EventAction action;

    private UUID reviewId;

    private String content;

    private List<UUID> attachedPhotoIds;

    private UUID userId;

    private UUID placeId;
}
