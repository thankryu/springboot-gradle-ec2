package com.gradle.springboot.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final CartDao cartDao;

    @PostMapping("/item/{id}")
    public String save(@PathVariable(name = "id") Long memberId, @RequestBody ItemDto itemDto){
        cartDao.addItem(itemDto, memberId);
        log.info("save cart to cache :" + memberId +" - [" + itemDto + "]");
        return "success caching";
    }

    @GetMapping("/item/{id}")
    public Object getByMemberId(@PathVariable(name = "id") Long memberId){
        log.info("find cart by member id :" + memberId);
        return cartDao.findByMemberId(memberId);
    }

    @PostMapping("/cache/{id}")
    public String saveTempList(@PathVariable(name = "id") Long memberId, @RequestBody ItemDto itemDto){
        System.out.println("시작");
        ItemDto tempResult =  cartDao.addTemp(itemDto, memberId);
        System.out.println("tempResult::"+ tempResult);
        log.info("save cart to cache :" + memberId +" - [" + itemDto + "]");
        return "success caching";
    }

    @Autowired
    TestService svc;

    @GetMapping("/cache/{id}")
    public String getTempList(@PathVariable(name = "id") Long memberId){
        ItemDto tempResult =  cartDao.getTemp(memberId);
        System.out.println("tempResult:::"+tempResult.toString());
        return "success caching";
    }

    @Cacheable(value = "TestVo", key = "#id", cacheManager = "cacheManager", unless = "#id == ''", condition = "#id.length > 2")
    @GetMapping("/getTest")
    public TestVo getData(@RequestParam String id ){

        return svc.getTestSvc(id);
    }

}