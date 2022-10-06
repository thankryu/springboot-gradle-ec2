package com.gradle.springboot.image.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDetailDto {
    private int gallery_seq;
    private int page_seq;
    private String file_path;
    private String file_name;
    private String file_etc;

    public ImageDetailDto(){
        this.gallery_seq = 0;
        this.page_seq = 0;
        this.file_path = "root";
        this.file_name = "ryu";
        this.file_etc = "etc";
    }
}
