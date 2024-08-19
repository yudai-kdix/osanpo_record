package com.example.osanpo.Controllers;

import com.example.osanpo.Entities.Image;
import com.example.osanpo.Services.ImageService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//XXX ここいる？？

@Controller
@RequestMapping("/images")
public class ImageController {
    
    private final ImageService imageService;


    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public String getAllImages(Model model) {
        List<Image> images = imageService.findAllImages();
        model.addAttribute("images", images);
        return "images/list";
    }

    @GetMapping("/{id}")
    public String getImageById(@PathVariable Long id, Model model) {
        Image image = imageService.findImageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid image Id:" + id));
        model.addAttribute("image", image);
        return "images/detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Image image) {
        return "images/create";
    }

    @PostMapping("/create")
    public String createImage(@ModelAttribute Image image) {
        imageService.saveImage(image);
        return "redirect:/images";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Image image = imageService.findImageById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid image Id:" + id));
        model.addAttribute("image", image);
        return "images/edit";
    }

    @PostMapping("/update/{id}")
    public String updateImage(@PathVariable Long id, @ModelAttribute Image image) {
        image.setId(id);
        imageService.saveImage(image);
        return "redirect:/images";
    }

    @GetMapping("/delete/{id}")
    public String deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return "redirect:/images";
    }
}