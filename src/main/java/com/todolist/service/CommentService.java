package com.todolist.service;

import com.todolist.dto.comment.CreateCommentRequest;
import com.todolist.dto.comment.CreateCommentResponse;
import com.todolist.entity.Comment;
import com.todolist.entity.Todo;
import com.todolist.repository.CommentRepository;
import com.todolist.validator.GlobalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final GlobalValidator globalValidator;

    @Transactional
    public CreateCommentResponse save(Long todoId,CreateCommentRequest request) {
        Todo todo =  globalValidator.findTodoOrException(todoId);
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
