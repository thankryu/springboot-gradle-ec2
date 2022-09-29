package com.gradle.main.service;

import com.gradle.main.vo.MainVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MainService")
public interface MainService {

    public List<MainVo> getAllMember();
    public String hello();
}
