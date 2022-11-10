package com.gradle.springboot.jpaTest.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

// 화면에서 필요한 결과는 PageResultDTO라는 이름으로 생성한다
@Data
// 다양한 곳에서 사용할 수 있도록 제네릭 타입을 이용해 DTO 와 EN(entity) 이라는 타입을 지정한다
public class PageResultDTO <DTO,EN>{

    private List<DTO> dtoList;
    // Function<EN,DTO> : 엔티티 객체들을 DTO로 변환해주는 기능
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
        // map안에서 fn 함수를 실행
        // fn 안에 entityToDto(entity) 함수가 담겨 있음
        // result.stream().map(fn) 까지 함수가 진행됨
        // stream -> list로 만들기 위해 .collect(Collectors.toList()) 를 사용
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }

}