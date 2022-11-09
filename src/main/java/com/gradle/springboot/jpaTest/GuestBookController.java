package com.gradle.springboot.jpaTest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController {

    @GetMapping({"/","/list"})
    public String list(){
        return "/guestbook/list";
    }

}
