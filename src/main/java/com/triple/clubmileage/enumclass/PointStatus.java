package com.triple.clubmileage.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointStatus {

    ADD("적립", "+1"),
    SUB("차감", "-1")
    ;

    private String title;

    private String detail;
}
