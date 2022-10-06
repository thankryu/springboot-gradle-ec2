package com.gradle.springboot.image;

import com.github.pagehelper.PageInfo;
import com.gradle.springboot.image.service.ImageService;
import com.gradle.springboot.image.vo.ImageDto;
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

    @RequestMapping("/readJson")
    public ImageDto readJsonTest(){
        System.out.println("파일 읽기");
        ImageDto idt = new ImageDto();
        idt.setGallery_seq(1);
        idt.setAuthor("test");
        return idt;
    }

//    @RequestMapping("/readImage")
//    public ImageDto selectGalleryList(){
//        return imageService.selectGalleryList();
//    }

    @RequestMapping("/readImage")
    public PageInfo<ImageDto> selectGalleryList() throws Exception{
        return new PageInfo<ImageDto>(imageService.getPageList());
    }

}
