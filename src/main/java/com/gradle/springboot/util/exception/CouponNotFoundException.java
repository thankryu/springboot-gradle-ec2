package com.gradle.springboot.util.exception;

public class CouponNotFoundException extends CustomException {

    private static final long serialVersionUID = -2116671122895194101L;

    public CouponNotFoundException() {
        super(ErrorCode.COUPON_NOT_FOUND);
    }
}