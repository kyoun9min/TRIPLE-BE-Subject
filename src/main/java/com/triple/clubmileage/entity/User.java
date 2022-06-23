package com.triple.clubmileage.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@ToString(exclude = {"pointHistoryList", "reviewList"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Long point;

    @OneToMany(mappedBy = "user")
    private List<PointHistory> pointHistoryList;

    @OneToMany(mappedBy = "user")
    private List<Review> reviewList;

    public User(UUID id) {
        this.id = id;
        this.point = 0L;
    }

}
