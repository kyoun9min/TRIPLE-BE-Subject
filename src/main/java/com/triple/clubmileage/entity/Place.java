package com.triple.clubmileage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString(exclude = "reviewList")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place extends BaseTimeEntity {

    @Id
    private String id;

    @OneToMany(mappedBy = "place")
    private List<Review> reviewList;

    public Place(String id) {
        this.id = id;
        reviewList = new ArrayList<>();
    }
}
