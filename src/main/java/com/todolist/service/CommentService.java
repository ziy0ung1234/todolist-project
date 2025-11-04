package com.todolist.service;

import com.todolist.dto.CreateCommentRequest;
import com.todolist.dto.CreateCommentResponse;
import com.todolist.entity.Comment;
import com.todolist.entity.Todo;
import com.todolist.repository.CommentRepository;
import com.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final TodoRepository todoRepository;
    private final CommentRepository commentRepository;

    //---- 유틸 메서드 -----
    private Todo findTodoOrException(Long todoId) {
        return todoRepository.findById(todoId).orElseThrow(
                ()-> new IllegalArgumentException("존재하지 않는 글입니다.")
        );
    }

    @Transactional
    public CreateCommentResponse save(Long todoId,CreateCommentRequest request) {
        Todo todo =  findTodoOrException(todoId);
        int commentCount = commentRepository.countByTodoId(todoId);
        if (commentCount >= 10) {
            throw new IllegalArgumentException("이 글에는 최대 10개의 댓글만 작성할 수 있습니다.");
        }
        Comment comment = new Comment(
                request.getContent(),
                request.getUsername(),
                request.getPassword(),
                todo // 실제 엔티티를 찾아 연결
        );
        Comment savedComment = commentRepository.save(comment);
        return new CreateCommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getUsername(),
                savedComment.getPassword(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }
}
