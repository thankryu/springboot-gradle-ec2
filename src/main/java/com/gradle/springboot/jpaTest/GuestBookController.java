package com.gradle.springboot.jpaTest;

import com.gradle.springboot.jpaTest.dto.PageRequestDTO;
import com.gradle.springboot.jpaTest.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor // 자동주입
@RequestMapping("/guestbook")
public class GuestBookController {

    private final GuestbookService service;

    @GetMapping("/")
    public String index(){
        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(@ModelAttribute PageRequestDTO pageRequestDTO , Model model){

        // 실제로 model에 추가되는 데이터 : PageResultDTO
        // => Model을 이용해 GuestbookServiecImpl에서 반환하는 PageResultDTO를 result 라는 이름으로 전달
        model.addAttribute("result", service.getList(pageRequestDTO));
    }

}
