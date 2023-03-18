package com.gradle.springboot.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

    @ResponseBody
    @GetMapping("/jwtLogin")
    public String jwtLoginAccess(UserVo userVo) {
        JwtToken jwtToken = new JwtToken();
        String token = jwtToken.makeJwtToken(userVo);

        return token;
    }

    @ResponseBody
    @GetMapping("/jwtLoginDecryption")
    public void jwtLoginDecryption(UserVo userVo) {

        String headerTest = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY3NDYzMTU2MCwiZXhwIjoxNjc0NjMzMzYwLCJpZCI6InJ5dTExMSIsImVtYWlsIjoidGhhbmtyeXVAZ21haWwuY29tMTExIiwicGFzc3dvcmQiOiIxMjM0MTExIn0.Xrmv-jVoVZ4YXp9nVS7KxZL2mW1ugkjuqD6fAV1ry0k";

        JwtToken jwtToken = new JwtToken();
        jwtToken.getUserVo(headerTest);
        UserVo UserVo = jwtToken.getUserVo(headerTest);

        System.out.println("UserVo::"+userVo.email);
        System.out.println("UserVo::"+userVo.id);
    }
}