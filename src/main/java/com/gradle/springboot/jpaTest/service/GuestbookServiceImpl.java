package com.gradle.springboot.jpaTest.service;

import com.gradle.springboot.jpaTest.Guestbook;
import com.gradle.springboot.jpaTest.dto.GuestbookDTO;
import com.gradle.springboot.jpaTest.dto.PageRequestDTO;
import com.gradle.springboot.jpaTest.dto.PageResultDTO;
import com.gradle.springboot.jpaTest.repository.GuestbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor // 의존성 자동주입
public class GuestbookServiceImpl implements GuestbookService{

    private final GuestbookRepository repository;
    // service interface에 구현된 dtoToEntity 활용
    @Override
    public Long register(GuestbookDTO dto) {
        // service interface에 구현된 dtoToEntity 활용
        Guestbook entity = dtoToEntity(dto);
        repository.save(entity);
        return entity.getGno();
    }
    
    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
        // 데이터 조회
        Page<Guestbook> result = repository.findAll(pageable);
        // Function
        // apply()라는 하나의 매개변수를 갖는 메서드가 있으며, 리턴값도 존재함. 이 인터페이스는 Function<T,R>로 정으되어 있어, Generic 타입을 두개 갖고 있다.
        // 앞에 있는 T는 입력 타입, 뒤에 있는 R은 리턴타입을 의미한다.
        // 즉 변환을 할 필요가 있을 때 이 인터페이스를 사용한다.
        Function<Guestbook,GuestbookDTO> fn = (entity -> entityToDto(entity));
        // JPA의 처리결과인 Page<Entity>와 Function을 전달해 엔티티 객체들을 DTO의 리스트로 변환하고 화면에 페이지 처리와 필요한 값들을 생성한다
        return new PageResultDTO<>(result,fn);
    }

    /**
     * 단일 내용 조회
     * @param gno
     * @return
     */
    @Override
    public Optional<Guestbook> findGuestbook(Long gno) {
        return repository.findById(gno);
    }

    /**
     * 단일 내용 삭제
     * @param gno
     */
    @Override
    public void deleteById(Long gno){
        repository.deleteById(gno);
    }

    @Override
    public void updateByGno(Long gno, GuestbookDTO dto) {
        Optional<Guestbook> guestbook = repository.findById(gno);

        if(guestbook.isPresent()){
            guestbook.get().setWriter(dto.getWriter());
            guestbook.get().setTitle(dto.getTitle());
            guestbook.get().setContent(dto.getContent());
            repository.save(dtoToEntity(dto));
        }
    }
}
