package com.todolist.repository;

import com.todolist.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    int countByTodoId(Long todoId); // 특정 일정 댓글 카운트 커스텀 쿼리 메소드
}