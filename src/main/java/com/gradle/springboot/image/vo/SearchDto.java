package com.gradle.springboot.image.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDto {
    private String searchKeyword;
    private String order;
    private String orderFlag;
    private int page;
    private int pageSize;
}
