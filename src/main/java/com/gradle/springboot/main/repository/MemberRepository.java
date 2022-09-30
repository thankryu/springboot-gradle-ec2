package com.gradle.springboot.main.repository;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.gradle.springboot.main.vo.MainVo;

@Mapper
public interface MemberRepository {
    List<MainVo> getAllMember();

}