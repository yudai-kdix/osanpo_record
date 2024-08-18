package com.example.osanpo.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/places")
public class PlaceController {
    
    @GetMapping("/places")
    public String getAllPlaces() {
        return "places/list";
    }
    @GetMapping("/create")
    public String showCreateForm() {
        return "places/create";
    }

    @PostMapping("/create")
    public String createPlace() {
        return "redirect:/places";
    }

}
