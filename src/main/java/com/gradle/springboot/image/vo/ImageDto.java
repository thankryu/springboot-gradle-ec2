package com.gradle.springboot.image.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDto {
    private int gallery_seq;
    private String author;
    private int type;
    private String del_yn;
    private String reg_date;
    private String tag;
    private String lang;
    private int view_cnt;
    private int like_cnt;
    private int cnt;

    private String file_path;
    private String file_name;
}