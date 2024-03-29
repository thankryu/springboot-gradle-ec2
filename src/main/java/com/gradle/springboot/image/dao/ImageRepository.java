package com.gradle.springboot.image.dao;

import com.github.pagehelper.Page;
import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import com.gradle.springboot.util.vo.RandomVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ImageRepository {
    List<ImageDto> selectGalleryAuthList();

    int selectGallerySeq();

    int insertGallery(ImageDto idto);

    int insertGalleryDetail(ImageDetailDto idd);

    ImageDto selectGalleryList();

    Page<ImageDto> getPageList(HashMap paramMap);

    List<ImageDetailDto> selectGalleryDetail(HashMap paramMap);

    void deleteGalleryDetail(ImageDto auth);

    void deleteGallery(ImageDto auth);

    RandomVo selectGalleryCnt();

    void updateGalleryViewCnt(int gallerySeq);

    int updateGalleryTag(ImageDto imageDto);
}
