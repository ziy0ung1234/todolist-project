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

/**
 * 댓글(Comment) 관련 비즈니스 로직을 담당하는 서비스 계층클래스
 *
 * <p><b>주요 역할:</b></p>
 * <ul>
 *   <li>댓글 작성 시 Todo 존재 여부 검증</li>
 *   <li>댓글 개수 제한(최대 10개) 정책 적용</li>
 *   <li>엔티티 생성 및 Repository 저장</li>
 * </ul>
 *
 * <p><b>트랜잭션 정책</b></p>
 * <ul>
 *   <li>댓글 생성은 단일 트랜잭션으로 처리</li>
 *   <li>예외 발생 시 전체 작업이 롤백</li>
 * </ul>
 *
 * <p><b>참고:</b></p>
 * <ul>
 *   <li>Todo 조회는 {@link GlobalValidator#findTodoOrException(Long)} 메서드로 수행</li>
 *   <li>데이터 접근은 {@link CommentRepository}가 담당</li>
 * </ul>
 */
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
