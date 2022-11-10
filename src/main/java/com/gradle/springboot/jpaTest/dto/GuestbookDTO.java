package com.gradle.springboot.jpaTest.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
// Entity클래스인 Guestbook과 거의 동일한 필드를 가지고 있으며 getter/setter를 통해 자유롭게 값을 변경할 수 있도록 구성한다
public class GuestbookDTO {
    private Long gno;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate, modDate;
}
