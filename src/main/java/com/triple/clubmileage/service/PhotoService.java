package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Photo;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.repository.PhotoRepository;
import com.triple.clubmileage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        List<String> attachedPhotoIds = eventDTO.getAttachedPhotoIds();
        for (String attachedPhotoId : attachedPhotoIds) {
            Photo photo = Photo.builder()
                    .id(attachedPhotoId)
                    .review(review)
                    .build();
            photoRepository.save(photo);
        }
    }

    public Photo read(String id) {
        return photoRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.BAD_REQUEST));
    }

    public List<Photo> getPhotoList(List<String> AttachedPhotoIds) {
        List<Photo> list = new ArrayList<>();

        for (String id : AttachedPhotoIds) {
            Photo photo = photoRepository.findById(id).get();
            list.add(photo);
        }
        return list;
    }

    public void delete(String id) {
        Photo photo = read(id);
        photoRepository.delete(photo);
    }
}
