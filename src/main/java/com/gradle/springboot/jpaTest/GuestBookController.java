package com.gradle.springboot.jpaTest;

import com.gradle.springboot.jpaTest.dto.GuestbookDTO;
import com.gradle.springboot.jpaTest.dto.PageRequestDTO;
import com.gradle.springboot.jpaTest.service.GuestbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    /**
     * 책 번호로 찾기
     * @param gno
     * @return
     */
    @GetMapping("/findBook/{gno}")
    public ResponseEntity<Guestbook> findGuestbook(@PathVariable Long gno){
        Optional<Guestbook> guestbookDTO = service.findGuestbook(gno);
        return new ResponseEntity<>(guestbookDTO.get(), HttpStatus.OK);
    }

    /**
     * 책번호로 삭제
     * @param gno
     */
    @DeleteMapping("/deleteById/{gno}")
    public void deleteById(@PathVariable Long gno){
        service.deleteById(gno);
    }

    /**
     * 책 번호로 수정
     * @param gno
     * @param guestbook
     */
    @PutMapping("/updateByGno/{gno}")
    public void updateByGno(@PathVariable Long gno, GuestbookDTO guestbook){
        service.updateByGno(gno, guestbook);
    }

}
