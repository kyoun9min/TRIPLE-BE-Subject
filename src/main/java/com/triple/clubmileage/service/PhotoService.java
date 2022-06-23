package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Photo;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.repository.PhotoRepository;
import com.triple.clubmileage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoService {

    public final PhotoRepository photoRepository;

    public final ReviewRepository reviewRepository;

    public void create(EventDTO eventDTO) {
        Review review = reviewRepository.findById(eventDTO.getReviewId()).get();
        List<UUID> attachedPhotoIds = eventDTO.getAttachedPhotoIds();
        for (UUID attachedPhotoId : attachedPhotoIds) {
            Photo photo = Photo.builder()
                    .id(attachedPhotoId)
                    .review(review)
                    .build();
            photoRepository.save(photo);
        }
    }

    public List<Photo> getPhotoList(List<UUID> AttachedPhotoIds) {
        List<Photo> list = new ArrayList<>();

        for (UUID id : AttachedPhotoIds) {
            Photo photo = photoRepository.findById(id).get();
            list.add(photo);
        }
        return list;
    }
}
