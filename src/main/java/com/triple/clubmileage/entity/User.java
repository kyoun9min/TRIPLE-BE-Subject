package com.triple.clubmileage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString(exclude = {"pointHistoryList", "reviewList"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String id;

    private Integer point;

    @OneToMany(mappedBy = "user")
    private List<PointHistory> pointHistoryList;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;

    public User(String id) {
        this.id = id;
        this.point = 0;
    }

    public void addPoint(int point) {
        this.point += point;
    }

    public void subPoint(int point) {
        this.point -= point;
    }
}
