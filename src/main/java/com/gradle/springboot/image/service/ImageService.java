package com.gradle.springboot.image.service;

import com.gradle.springboot.image.vo.ImageDto;
import org.springframework.stereotype.Service;

@Service("ImageService")
public interface ImageService {

    ImageDto selectGalleyList();

    int readGalleryList();

}
