package com.example.osanpo.Controllers;

import java.nio.file.Path;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.osanpo.Entities.Image;
import com.example.osanpo.Entities.Place;
import com.example.osanpo.Services.ImageService;
import com.example.osanpo.Services.PlaceService;

@Controller
@RequestMapping("/places")
public class PlaceController {

    ImageService imageService;
    PlaceService placeService;
    
    @GetMapping("/places")
    public String getAllPlaces() {
        return "places/list";
    }
    @GetMapping("/create")
    public String showCreateForm() {
        return "places/create";
    }

    @PostMapping("/create")
    public String createPlace(@ModelAttribute("file") Image image, @ModelAttribute("place") Place place) {
        Path path = imageService.saveImage(image);
        place.setImage(path.toString());
        placeService.savePlace(place);
        return "redirect:/places";
    }
}
