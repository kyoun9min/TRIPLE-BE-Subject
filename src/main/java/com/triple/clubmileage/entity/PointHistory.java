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

    @Enumerated(EnumType.STRING)
    private String status;

    @Enumerated(EnumType.STRING)
    private String detail;

    @Enumerated(EnumType.STRING)
    private String reason;

    @Builder
    public PointHistory(User user, String status, String detail, String reason) {
        this.user = user;
        this.status = status;
        this.detail = detail;
        this.reason = reason;
    }
}
