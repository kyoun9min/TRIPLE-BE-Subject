package com.triple.clubmileage.service;

import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Photo;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.exception.PhotoNotFoundException;
import com.triple.clubmileage.exception.ReviewNotFoundException;
import com.triple.clubmileage.repository.PhotoRepository;
import com.triple.clubmileage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService {

    public final PhotoRepository photoRepository;

    public final ReviewRepository reviewRepository;

    public void create(EventDTO eventDTO) {
        Review review = reviewRepository.findById(eventDTO.getReviewId())
                .orElseThrow(() -> new ReviewNotFoundException("해당 리뷰가 존재하지 않습니다."));
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
                .orElseThrow(() -> new PhotoNotFoundException("해당 사진이 존재하지 않습니다."));
    }

    public List<Photo> getPhotoList(List<String> AttachedPhotoIds) {
        List<Photo> list = new ArrayList<>();

        for (String id : AttachedPhotoIds) {
            Photo photo = photoRepository.findById(id)
                    .orElseThrow(() -> new PhotoNotFoundException("해당 사진이 존재하지 않습니다."));
            list.add(photo);
        }
        return list;
    }

    public void delete(String id) {
        Photo photo = read(id);
        photoRepository.delete(photo);
    }
}
