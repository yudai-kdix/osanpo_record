package com.example.osanpo.Controllers;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    ImageService imageService;
    @Autowired
    PlaceService placeService;
    
    @GetMapping
    public String getAllPlaces(Model model) {
        model.addAttribute("places", placeService.getAllPlaces());
        return "places/list";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("place", new Place());
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
