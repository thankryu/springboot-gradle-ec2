package com.gradle.springboot.cart;

import lombok.Data;

import java.io.Serializable;

@Data
public class ItemDto implements Serializable {
    private String name;
    private int price;
    private int quantity;
}