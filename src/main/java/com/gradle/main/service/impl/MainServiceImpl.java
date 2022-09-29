package com.gradle.main.service.impl;

import com.gradle.main.repository.MemberRepository;
import com.gradle.main.service.MainService;
import com.gradle.main.vo.MainVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MainService")
public class MainServiceImpl implements MainService {

    @Autowired
    private MemberRepository memberDao;

    @Override
    public List<MainVo> getAllMember(){
        return memberDao.getAllMember();
    }

    @Override
    public String hello() {
        return "hello world";
    }

}
