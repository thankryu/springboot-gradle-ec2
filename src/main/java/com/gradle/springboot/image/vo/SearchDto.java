package com.gradle.springboot.image.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private String search_keyword;
    private String order;
    private String order_flag;
}
