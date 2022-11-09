package com.gradle.springboot.jpaTest;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestBook extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // index 자동생성
    private Long gno;

    @Column(length = 100,nullable = false)
    private String title;

    @Column(length = 1500,nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;
}
