package com.gradle.springboot.main.dao;

import com.gradle.springboot.main.vo.MainVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRepository {
    List<MainVo> getAllMember();

}