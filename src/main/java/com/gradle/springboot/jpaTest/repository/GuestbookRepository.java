package com.gradle.springboot.jpaTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gradle.springboot.jpaTest.Guestbook;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface GuestbookRepository extends JpaRepository<Guestbook,Long>, QuerydslPredicateExecutor<Guestbook> {
}
