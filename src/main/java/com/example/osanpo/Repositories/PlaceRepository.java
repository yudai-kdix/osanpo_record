package com.example.osanpo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.osanpo.Entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
}