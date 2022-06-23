package com.triple.clubmileage.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@ToString(exclude = {"photoList", "user", "place"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String content;

    private Integer point;

    @OneToMany(mappedBy = "review")
    private List<Photo> photoList;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;

    @Builder
    public Review(UUID id, String content, Integer point, List<Photo> photoList, User user, Place place) {
        this.id = id;
        this.content = content;
        this.point = point;
        this.photoList = photoList;
        this.user = user;
        this.place = place;
    }
}
