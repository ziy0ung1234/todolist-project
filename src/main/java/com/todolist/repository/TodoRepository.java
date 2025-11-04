package com.todolist.repository;

import com.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 전체 일정 조회, 수정일 기준 내림차순
    // SELECT t FROM Todo t ORDER BY t.createdAt DESC
    List<Todo> findAllByOrderByCreatedAtDesc();

    // 특정 작성자(username) 일정 조회, 수정일 기준 내림차순
    // SELECT t FROM Todo t WHERE t.username = :username ORDER BY t.createdAt DESC
    List<Todo> findAllByUsernameOrderByCreatedAtDesc(String username);
}