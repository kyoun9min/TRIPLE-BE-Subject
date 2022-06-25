package com.triple.clubmileage.entity;

import com.triple.clubmileage.dto.EventDTO;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(exclude = {"photoList", "user", "place"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name = "idx_user", columnList = "user_id, place_id"))
public class Review extends BaseTimeEntity {

    @Id
    private String id;

    private String content;

    private Integer photoNumber;

    private Integer point;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    private List<Photo> photoList;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    @Builder
    public Review(String id, String content, Integer photoNumber, Integer point, List<Photo> photoList, User user, Place place) {
        this.id = id;
        this.content = content;
        this.photoNumber = photoNumber;
        this.point = point;
        this.photoList = photoList;
        this.user = user;
        this.place = place;
    }

    public void updateReview(EventDTO eventDTO, List<Photo> photoList) {
        this.content = eventDTO.getContent();
        this.photoList = photoList;
        this.photoNumber = photoList.size();
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void subPoint(int point) {
        this.point -= point;
    }

}
