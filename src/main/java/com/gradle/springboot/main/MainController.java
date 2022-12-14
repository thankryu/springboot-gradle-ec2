package com.gradle.springboot.main;

import com.gradle.springboot.main.service.MainService;
import com.gradle.springboot.main.vo.MainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private MainService mainService;

    @RequestMapping(value="/abc")
    public String index(Model model) {

        List<MainVo> memberList = mainService.getAllMember();
        System.out.println("size: " + memberList.size());

        for(int i=0; i < memberList.size(); i++){
            System.out.println("test123");
            System.out.println(memberList.get(i).getUSER_SEQ());
            System.out.println(memberList.get(i).getUSER_NAME());
        }

        model.addAttribute("memberList",memberList);
        return "index";
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello!!!!!";
    }
}
