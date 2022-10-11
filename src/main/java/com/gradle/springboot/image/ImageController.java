package com.gradle.springboot.image;

import com.github.pagehelper.PageInfo;
import com.gradle.springboot.image.service.ImageService;
import com.gradle.springboot.image.vo.ImageDetailDto;
import com.gradle.springboot.image.vo.ImageDto;
import com.gradle.springboot.image.vo.SearchDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @RequestMapping("/galleryList")
    public String imageIndex(){
        return "image/galleryList";
    }

    @RequestMapping("/galleryDetail/{gallerySeq}")
    public String imageDetail(Model model, @PathVariable("gallerySeq") int gallerySeq){
        model.addAttribute("gallerySeq", gallerySeq);
        return "image/galleryDetail";
    }

    @RequestMapping("/readFile")
    public int readGalleryList(){
        System.out.println("파일 읽기");
        return imageService.readGalleryList();
    }

    @ResponseBody
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
    @ResponseBody
    @RequestMapping("/readImage")
    public PageInfo<ImageDto> selectGalleryList(SearchDto searchDto) throws Exception{
        return new PageInfo<ImageDto>(imageService.getPageList(searchDto));
    }

    @ResponseBody
    @RequestMapping("/readImageDetail/{gallerySeq}")
    public List<ImageDetailDto> selectGalleryDetail(@PathVariable("gallerySeq") int gallerySeq) throws Exception{
        return imageService.selectGalleryDetail(gallerySeq);
    }

}
