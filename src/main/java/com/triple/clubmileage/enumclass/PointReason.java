package com.triple.clubmileage.enumclass;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PointReason {

    WRITE_TEXT("1자 이상 텍스트 작성"),
    ATTACH_PHOTO("1장 이상 사진 첨부"),
    FIRST_REVIEW("첫 리뷰 작성"),
    DELETE_PHOTO("사진 모두 삭제"),
    DELETE_REVIEW("리뷰 삭제")
    ;

    private String description;
}
