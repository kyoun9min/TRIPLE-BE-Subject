package com.triple.clubmileage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString(exclude = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private String status;

    private String value;

    private String reason;

    @Builder
    public PointHistory(User user, String status, String value, String reason) {
        this.user = user;
        this.status = status;
        this.value = value;
        this.reason = reason;
    }
}
