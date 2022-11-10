package com.gradle.springboot.jpaTest;

import com.gradle.springboot.jpaTest.dto.GuestbookDTO;
import com.gradle.springboot.jpaTest.dto.PageRequestDTO;
import com.gradle.springboot.jpaTest.dto.PageResultDTO;
import com.gradle.springboot.jpaTest.repository.GuestbookRepository;
import com.gradle.springboot.jpaTest.service.GuestbookService;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import com.querydsl.core.types.dsl.BooleanExpression;

import org.springframework.data.domain.Page;


import java.util.Optional;
import java.util.stream.IntStream;


@SpringBootTest
public class GuestbookRepositoryTests {

    @Autowired
    private GuestbookRepository guestbookRepository;

    @Autowired
    private GuestbookService service;

    @Test // 더미데이터생성
    public void insertDDumies(){

        IntStream.rangeClosed(1,300).forEach(i -> {

            Guestbook guestbook = Guestbook.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            guestbookRepository.save(guestbook);
        });
    }

    @Test // 수정시간 테스트
    public void updateTest(){

        // 300 번 선택
        Optional<Guestbook> result = guestbookRepository.findById(300L);

        // 존재하는 번호로 테스트
        // isPresent() :  ! = null 과 같다
        if (result.isPresent()){

            Guestbook guestbook = result.get();

            guestbook.changeTitle("Changed Title...");
            guestbook.changeContent("Changed Content...");

            guestbookRepository.save(guestbook);
        }
    }

    @Test // Querydsl 테스트 - 단일항목테스트(title 에 1이 포함된 내용 출력)
    public void testQueryy1(){

        // 페이징 처리
        Pageable pageable = PageRequest.of(0,10, Sort.by("gno").descending());

        // findAll -> JpaRepository but Querydsl -> QuerydslPredicateExecutor 사용

        // Predicate
        // Q도메인 클래스를 사용하면 엔티티 클래스에 선언된 필드를 변수로 사용할 수 있다
        QGuestbook qGuestbook = QGuestbook.guestbook;

        // BooleanBuilder : 쿼리의 where문에 들어가는 조건을 넣어주는 컨테이너
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // title like %1% 쿼리를 코드로 구현
        BooleanExpression expression = qGuestbook.title.contains("1");

        // 만들어진 조건 결합
        booleanBuilder.and(expression);

        // BooleanBuilder는 guestbookRepository에 추가된 QuerydslPredicateExecutor 인터페이스의 findAll()을 사용할 수 있다
        Page<Guestbook> result = guestbookRepository.findAll(booleanBuilder,pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test // Querydsl 테스트 - 다중항목테스트(title 이나 content 에 1이 포함된 내용 출력)
    public void testQueryy2() {

        // 페이징 처리
        Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());

        QGuestbook qGuestbook = QGuestbook.guestbook;

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // title like %1% or content like %1% 쿼리를 코드로 구현
        BooleanExpression expression = qGuestbook.title.contains("1");
        BooleanExpression exAll = expression.or(qGuestbook.content.contains("1"));

        // 만들어진 조건 결합
        booleanBuilder.and(exAll);
        booleanBuilder.and(qGuestbook.gno.gt(0L));

        Page<Guestbook> result = guestbookRepository.findAll(booleanBuilder, pageable);

        result.stream().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }


    /**
     * 등록 및 DTO -> Entity 변환테스트
     */
    @Test
    public void testRegister(){

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title...")
                .content("Sample Content...")
                .writer("user0")
                .build();
        System.out.println(service.register(guestbookDTO));
    }

    /**
     * Entity 객체들을 DTO 객체로 변환 테스트
     */
    @Test
    public void testList(){

        // PageRequestDTO에 값을 담기
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        
        // entity로 값을 가져온 후 dto로 치환하여 가져오기 
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        // 출력
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
}
