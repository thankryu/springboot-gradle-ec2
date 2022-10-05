package com.gradle.springboot.image.dao;

import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ImageRepository {
    List<ImageDto> selectGalleryAuthList();

    int selectGallerySeq();

    int insertGallery(ImageDto idto);

    int insertGalleryDetail(ImageDetailDto idd);
}
