package com.gradle.springboot.image.service;

import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import com.gradle.springboot.image.vo.SearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ImageService")
public interface ImageService {

    int readGalleryList();

    ImageDto selectGalleryList();

    List<ImageDto> getPageList(SearchDto searchDto);

    List<ImageDetailDto> selectGalleryDetail(int gallerySeq);
}
