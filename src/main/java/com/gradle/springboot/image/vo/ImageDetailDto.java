package com.gradle.springboot.image.vo;

import lombok.Data;

@Data
public class ImageDetailDto {
    private int gallery_seq;
    private int page_seq;
    private String file_path;
    private String file_name;
    private String file_etc;
}
