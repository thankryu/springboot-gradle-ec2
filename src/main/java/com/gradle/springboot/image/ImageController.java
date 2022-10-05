package com.gradle.springboot.image;

import com.gradle.springboot.image.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/readFile")
    public int readGalleryList(){
        System.out.println("파일 읽기");
        return imageService.readGalleryList();
    }
}
