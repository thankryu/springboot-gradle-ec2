package com.gradle.springboot.login;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    public String id;
    public String email;
    public String password;

    public UserVo(Claims claims){
        this.id = (String) claims.get("id");
        this.email = (String) claims.get("email");
        this.password = (String) claims.get("password");
    }

}