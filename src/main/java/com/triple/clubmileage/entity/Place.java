package com.triple.clubmileage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(exclude = "reviewList")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {

    @Id
    private String id;

    @OneToMany(mappedBy = "place")
    private List<Review> reviewList;
}
