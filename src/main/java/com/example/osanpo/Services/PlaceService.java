package com.example.osanpo.Services;


import org.springframework.stereotype.Service;

import com.example.osanpo.Entities.Place;
import com.example.osanpo.Exceptions.ResourceNotFoundException;
import com.example.osanpo.Repositories.PlaceRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PlaceService {


    private final PlaceRepository placeRepository;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    public List<Place> getAllPlaces() {
        return placeRepository.findAll();
    }

    public Optional<Place> getPlaceById(Long id) {
        return placeRepository.findById(id);
    }

    public Place createPlace(Place place) {
        return placeRepository.save(place);
    }

    public Place updatePlace(Long id, Place placeDetails) {
        return placeRepository.findById(id)
                .map(place -> {
                    place.setImageUrl(placeDetails.getImageUrl());
                    place.setDescription(placeDetails.getDescription());
                    place.setTakenAt(placeDetails.getTakenAt());
                    place.setLatitude(placeDetails.getLatitude());
                    place.setLongitude(placeDetails.getLongitude());
                    place.setPrefecture(placeDetails.getPrefecture());
                    place.setCity(placeDetails.getCity());
                    place.setWard(placeDetails.getWard());
                    place.setNearestStation(placeDetails.getNearestStation());
                    return placeRepository.save(place);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Place not found with id " + id));
    }

    public void deletePlace(Long id) {
        placeRepository.deleteById(id);
    }

    // public List<Place> findPlacesByCity(String city) {
    //     return placeRepository.findByCity(city);
    // }

    // public List<Place> findPlacesByCoordinates(Double latitude, Double longitude) {
    //     return placeRepository.findByLatitudeAndLongitude(latitude, longitude);
    // }
}