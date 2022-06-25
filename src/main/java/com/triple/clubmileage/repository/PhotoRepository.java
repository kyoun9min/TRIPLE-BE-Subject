package com.triple.clubmileage.repository;

import com.triple.clubmileage.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
}
