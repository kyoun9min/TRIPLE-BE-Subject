package com.triple.clubmileage.service;

import com.triple.clubmileage.component.DummyObjectInit;
import com.triple.clubmileage.dto.EventDTO;
import com.triple.clubmileage.entity.Place;
import com.triple.clubmileage.entity.Review;
import com.triple.clubmileage.entity.User;
import com.triple.clubmileage.enumclass.EventAction;
import com.triple.clubmileage.repository.ReviewRepository;
import com.triple.clubmileage.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ReviewServiceTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private DummyObjectInit dummyObjectInit;

    @Test
    @DisplayName("1자 이상 텍스트 작성한 리뷰 작성 +1")
    void writeTextReview() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("좋아요!");
        dummyObjectInit.init(eventDTO);
        createPlaceReview(eventDTO);

        // when
        reviewService.create(eventDTO);

        // then
        User user = userService.read(eventDTO.getUserId());
        assertThat(1).isEqualTo(user.getPoint());
    }

    @Test
    @DisplayName("1장 이상 사진 첨부한 리뷰 작성 +1")
    void writePhotoReview() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("");
        eventDTO.getAttachedPhotoIds().add(UUID.randomUUID().toString());
        dummyObjectInit.init(eventDTO);
        createPlaceReview(eventDTO);

        // when
        reviewService.create(eventDTO);

        // then
        User user = userService.read(eventDTO.getUserId());
        assertThat(1).isEqualTo(user.getPoint());
    }

    @Test
    @DisplayName("해당 장소 첫 번째 리뷰 작성 +1")
    void writeFirstReview() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("");
        dummyObjectInit.init(eventDTO);

        // when
        reviewService.create(eventDTO);

        // then
        User user = userService.read(eventDTO.getUserId());
        assertThat(1).isEqualTo(user.getPoint());
    }

    @Test
    @DisplayName("텍스트, 사진, 첫 번째 리뷰 조건 모두 만족하는 리뷰 작성 +3")
    void writeTextAndPhotoAndFirstReview() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("좋아요!");
        eventDTO.getAttachedPhotoIds().add(UUID.randomUUID().toString());
        dummyObjectInit.init(eventDTO);

        // when
        reviewService.create(eventDTO);

        // then
        User user = userService.read(eventDTO.getUserId());
        assertThat(3).isEqualTo(user.getPoint());
    }

    @Test
    @DisplayName("글만 작성한 리뷰에 사진 추가하는 리뷰 수정 +1")
    void updateReviewWithAddPhoto() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("좋아요!");
        dummyObjectInit.init(eventDTO);
        reviewService.create(eventDTO);
        User preUser = userService.read(eventDTO.getUserId());

        eventDTO.setAction(EventAction.MOD);
        eventDTO.getAttachedPhotoIds().add(UUID.randomUUID().toString());

        // when
        reviewService.update(eventDTO);

        // then
        User currUser = userService.read(eventDTO.getUserId());
        assertThat(2).isEqualTo(preUser.getPoint());
        assertThat(3).isEqualTo(currUser.getPoint());
    }

    @Test
    @DisplayName("글과 사진이 있는 리뷰에서 사진을 모두 삭제하는 리뷰 수정 -1")
    void updateReviewWithDeletePhoto() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("좋아요!");
        eventDTO.getAttachedPhotoIds().add(UUID.randomUUID().toString());
        dummyObjectInit.init(eventDTO);
        reviewService.create(eventDTO);
        User preUser = userService.read(eventDTO.getUserId());

        eventDTO.setAction(EventAction.MOD);
        eventDTO.getAttachedPhotoIds().remove(0);

        // when
        reviewService.update(eventDTO);

        // then
        User currUser = userService.read(eventDTO.getUserId());
        assertThat(3).isEqualTo(preUser.getPoint());
        assertThat(2).isEqualTo(currUser.getPoint());
    }

    @Test
    @DisplayName("리뷰 삭제 -N")
    void deleteReview() {

        // given
        EventDTO eventDTO = createDTO();
        eventDTO.setAction(EventAction.ADD);
        eventDTO.setContent("좋아요!");
        eventDTO.getAttachedPhotoIds().add(UUID.randomUUID().toString());
        dummyObjectInit.init(eventDTO);
        reviewService.create(eventDTO);
        User preUser = userService.read(eventDTO.getUserId());

        eventDTO.setAction(EventAction.DELETE);

        // when
        reviewService.delete(eventDTO);

        // then
        User currUser = userService.read(eventDTO.getUserId());
        assertThat(3).isEqualTo(preUser.getPoint());
        assertThat(0).isEqualTo(currUser.getPoint());
    }

    public EventDTO createDTO() {
        return EventDTO.builder()
                .reviewId(UUID.randomUUID().toString())
                .userId(UUID.randomUUID().toString())
                .placeId(UUID.randomUUID().toString())
                .build();
    }

    public void createPlaceReview(EventDTO eventDTO) {
        Place place = placeService.read(eventDTO.getPlaceId());
        User user = new User(UUID.randomUUID().toString());
        userRepository.save(user);
        Review review = Review.builder()
                .id(UUID.randomUUID().toString())
                .content("최고에요!")
                .photoNumber(2)
                .point(3)
                .photoList(new ArrayList<>())
                .user(user)
                .place(place)
                .build();
        reviewRepository.save(review);
    }

    public void createReview(EventDTO eventDTO) {

    }
}