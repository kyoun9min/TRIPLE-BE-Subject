package com.triple.clubmileage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {

    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Review review;

    @Builder
    public Photo(String id, Review review) {
        this.id = id;
        this.review = review;
    }
}
