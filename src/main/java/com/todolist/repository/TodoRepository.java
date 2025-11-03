package com.todolist.repository;

import com.todolist.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 전체 일정 조회 (User까지 한번에 가져오기 — N+1 방지)
    @Query("SELECT t FROM Todo t JOIN FETCH t.user ORDER BY t.createdAt DESC")
    List<Todo> findAllWithUser();
    List<Todo> findAllByUser_UsernameOrderByCreatedAtDesc(String username);
}
