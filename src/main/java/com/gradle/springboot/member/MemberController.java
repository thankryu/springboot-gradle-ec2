package com.gradle.springboot.member;

import com.gradle.springboot.util.exception.CouponExpirationException;
import com.gradle.springboot.util.exception.CouponNotFoundException;
import com.gradle.springboot.util.exception.InvalidParameterException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @GetMapping("/memberTest")
    public String memberException(@Valid Member dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new InvalidParameterException(result);
        }

        return "page/home";
    }

    @GetMapping("/exception")
    public String exceptionTest(String code) {
        switch (code) {
            case "1":
                throw new CouponExpirationException();
            case "2":
                throw new CouponNotFoundException();
            case "3":
                int a = 3 / 0;
                break;
        }
        return "page/home";
    }
}
