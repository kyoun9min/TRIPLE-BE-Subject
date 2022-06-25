package com.triple.clubmileage.repository;

import com.triple.clubmileage.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

    boolean existsByPlaceIdAndUserId(String placeId, String userId);
}
