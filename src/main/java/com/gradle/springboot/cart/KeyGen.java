package com.gradle.springboot.cart;

public class KeyGen {
    private static final String CART_KEY = "cart";

    public static String cartKeyGenerate(Long memberId) {
        return CART_KEY + ":" + memberId;
    }
}