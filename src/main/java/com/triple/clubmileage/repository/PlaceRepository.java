package com.triple.clubmileage.repository;

import com.triple.clubmileage.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlaceRepository extends JpaRepository<Place, String> {
}
