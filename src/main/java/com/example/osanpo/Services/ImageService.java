package com.example.osanpo.Services;


import com.example.osanpo.Entities.Image;
import com.example.osanpo.Repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;


    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> findImageById(Long id) {
        return imageRepository.findById(id);
    }

    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public List<Image> findImagesByPostId(Long postId) {
        return imageRepository.findAll().stream()
                .filter(image -> image.getPost().getId().equals(postId))
                .toList();
    }
}