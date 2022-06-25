## 트리플여행자 클럽 마일리지 서비스

트리플 사용자들이 장소에 리뷰를 작성할 때 포인트를 부여하고, 전체/개인에 대한 포인트 부여 히스토리와 개인별
누적 포인트를 관리하고자 합니다.


## 실행 방법

**DB 설정**

- `src/main/resources/application.yml` 로 이동
- DB `url`, `username`, `password` 설정
- DDL 위치 : `sql-data/schema.sql`
- `ddl-auto` 옵션 `create` 로 변경 시 스키마 자동 생성

![image](https://user-images.githubusercontent.com/60173868/175759571-10ce378d-c680-43a6-980a-e07b08e5e88e.png)

**빌드 및 실행**

- 콘솔창에서 해당 프로젝트 폴더로 이동
- `./gradlew build`
- `cd build/libs`
- `java -jar clubmileage-0.0.1-SNAPSHOT.jar`


## 기능

`POST` `http://localhost:8080/events` 호출 시 해당 User ID와 Place ID를 가진 더미 객체가 자동 생성됩니다.

```
POST /events
{
  "type": "REVIEW",
  "action": "ADD", /* "MOD", "DELETE" */
  "reviewId": "240a0658-dc5f-4878-9381-ebb7b2667772",
  "content": "좋아요!",
  "attachedPhotoIds": ["e4d1a64e-a531-46de-88d0-ff0ed70c0bb8", "afb0cef2-
  851d-4a50-bb07-9cc15cbdc332"],
  "userId": "3ede0ef2-92b7-4817-a5f3-0c575361f745",
  "placeId": "2e4baf1c-5acb-4efb-a1af-eddada31b00f"
}
```

### 리뷰 생성
- 리뷰가 생성됩니다.
- 1자 이상 텍스트 작성 시 +1점이 부여됩니다.
- 1장 이상 사진 첨부 시 +1점이 부여됩니다.
- 특정 장소에 작성한 첫 리뷰일 시 +1점이 부여됩니다.
### 리뷰 수정
- 리뷰 내용과 사진목록이 수정됩니다.
- 글만 작성한 리뷰에 사진을 추가하면 +1점이 부여됩니다.
- 글과 사진이 있는 리뷰에서 사진을 모두 삭제하면 -1점 차감됩니다.
### 리뷰 삭제
- 리뷰와 사진들을 모두 삭제합니다.
- 해당 리뷰로 받았던 모든 포인트가 차감됩니다.
### 포인트 조회
`GET` `http://localhost:8080/point/{userId}` 로 포인트 조회를 호출합니다.
- 현재 유저가 보유한 총 포인트가 조회됩니다.
- 포인트 증감이력은 point_history 테이블에 저장됩니다.

![image](https://user-images.githubusercontent.com/60173868/175761424-e20ce26d-79aa-4e93-84a4-c08776160dbd.png)


# ERD

![image](https://user-images.githubusercontent.com/60173868/175761314-0875937f-881a-4f63-b2bb-64949e6405c3.png)


